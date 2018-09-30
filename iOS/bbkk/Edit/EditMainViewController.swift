//
//  EditMainViewController.swift
//  bbkk
//
//  Created by 이재성 on 24/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit

class EditMainViewController: UIViewController {
    @IBOutlet weak var indicator: UIActivityIndicatorView!
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var contentTextView: UITextView!
    @IBOutlet weak var locationTextField: UITextField!
    @IBOutlet weak var subNameTextField: UITextField!
    @IBOutlet weak var titleTextView: UITextView!
    @IBOutlet weak var pageControl: UIPageControl!
    var items: [UIImage] = []
    @IBOutlet weak var completeButton: UIBarButtonItem!
    
    @IBAction func backButtonAction(_ sender: Any) {
        navigationController?.popViewController(animated: true)
    }
    
    @IBAction func completeButtonAction(_ sender: Any) {
        indicator.startAnimating()
        indicator.isHidden = false
        completeButton.isEnabled = false
        
        postFeedRegister(title: titleTextView.text, content: contentTextView.text, category: subNameTextField.text ?? "", season: UserDefaults.standard.string(forKey: "seasons") ?? "SPRING", position: locationTextField.text ?? "", images: encodeImagesBase64()) { [weak self] in
            DispatchQueue.main.async {
                self?.dismiss(animated: true, completion: nil)
            }
        }
    }
    
    private func encodeImagesBase64() -> String {
        var encodeImages: String = ""
        items.forEach {
            if let imageData: NSData = $0.pngData() as NSData? {
                let strBase64 = imageData.base64EncodedString(options: .lineLength64Characters)
                if encodeImages == "" {
                    encodeImages = "\(strBase64)"
                } else {
                    encodeImages = "\(encodeImages),\(strBase64)"
                }
            }
        }
        
        return encodeImages
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.titleView = UIImageView(image: #imageLiteral(resourceName: "icon_title_heart.png"))
        hideKeyboardWhenTappedAround()
        pageControl.numberOfPages = items.count
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        registerNotifications()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        unregisterNotifications()
    }
    
    func registerNotifications() {
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow), name:UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillHide), name:UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    func unregisterNotifications() {
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillShowNotification, object: nil)
        NotificationCenter.default.removeObserver(self, name: UIResponder.keyboardWillHideNotification, object: nil)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "imagePickerSegue" {
            if let pageViewController = segue.destination as? UIPageViewController,
                let startViewController = viewControllerAtIndex(0) {
                pageViewController.dataSource = self
                pageViewController.delegate = self
                let viewControllers: [ImagePageViewController] = [startViewController]
                pageViewController.setViewControllers(viewControllers, direction: .forward, animated: true, completion: nil)
            }
        }
    }
    
    private func viewControllerAtIndex(_ index: Int) -> ImagePageViewController? {
        let storyboard = UIStoryboard(name: "ViewPager", bundle: nil)
        guard let imagePageViewController = storyboard.instantiateViewController(withIdentifier: "ImagePageViewController") as? ImagePageViewController else { return nil }
        
        imagePageViewController.pageIndex = index
        imagePageViewController.img = items[index]
        
        return imagePageViewController
    }
}

extension EditMainViewController {
    @objc func keyboardWillShow(notification:NSNotification) {
        guard let userInfo = notification.userInfo else { return }
        var keyboardFrame:CGRect = (userInfo[UIResponder.keyboardFrameBeginUserInfoKey] as! NSValue).cgRectValue
        keyboardFrame = self.view.convert(keyboardFrame, from: nil)
        
        var contentInset:UIEdgeInsets = scrollView.contentInset
        contentInset.bottom = keyboardFrame.size.height
        scrollView.contentInset = contentInset
    }
    
    @objc func keyboardWillHide(notification:NSNotification) {
        let contentInset: UIEdgeInsets = UIEdgeInsets.zero
        scrollView.contentInset = contentInset
    }
}

extension EditMainViewController: UIPageViewControllerDelegate {
    func pageViewController(_ pageViewController: UIPageViewController, didFinishAnimating finished: Bool, previousViewControllers: [UIViewController], transitionCompleted completed: Bool) {
        guard let pageContentViewController = pageViewController.viewControllers?[0] as? ImagePageViewController else { return }
        pageControl.currentPage = pageContentViewController.pageIndex ?? 0
    }
}

extension EditMainViewController: UIPageViewControllerDataSource {
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerBefore viewController: UIViewController) -> UIViewController? {
        guard let viewController = viewController as? ImagePageViewController, let index = viewController.pageIndex, index > 0 else { return nil }

        return viewControllerAtIndex(index - 1)
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerAfter viewController: UIViewController) -> UIViewController? {
        guard let viewController = viewController as? ImagePageViewController, let index = viewController.pageIndex, index < items.count - 1 else { return nil }

        return viewControllerAtIndex(index + 1)
    }
    
    func presentationCountForPageViewController(pageViewController: UIPageViewController) -> Int {
        return items.count
    }
    
    func presentationIndexForPageViewController(pageViewController: UIPageViewController) -> Int {
        return 0
    }
}

extension EditMainViewController: UITextViewDelegate {
    func textViewShouldBeginEditing(_ textView: UITextView) -> Bool {
        if textView.tag == 149 {
            if textView.text == "해당 장소를 감성적으로 표현해주세요" {
                textView.text = nil
                textView.textColor = #colorLiteral(red: 0.1333333333, green: 0.1333333333, blue: 0.1333333333, alpha: 1)
            }
        }
        if textView.tag == 150 {
        textView.backgroundColor = .white
        }
        
        return true
    }
    
    func textViewShouldEndEditing(_ textView: UITextView) -> Bool {
        if textView.tag == 149 {
            if textView.text.isEmpty || textView.text == "" {
                UIView.animate(withDuration: 0.3, delay: 0, options: .curveEaseInOut, animations: {
                    textView.textColor = #colorLiteral(red: 0.4392156863, green: 0.4392156863, blue: 0.4392156863, alpha: 1)
                    textView.text = "해당 장소를 감성적으로 표현해주세요"
                })
            }
        }
        if textView.tag == 150, textView.text.count == 0 {
            UIView.animate(withDuration: 0.3, delay: 0, options: .curveEaseInOut, animations: {
                textView.backgroundColor = .clear
            })
        }
        
        return true
    }
}
