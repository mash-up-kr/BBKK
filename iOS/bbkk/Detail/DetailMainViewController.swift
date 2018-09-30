//
//  DetailMainViewController.swift
//  bbkk
//
//  Created by 이재성 on 25/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit

class DetailMainViewController: UIViewController {
    @IBOutlet weak var detailCommentCount: UILabel!
    @IBOutlet weak var detailContent: UILabel!
    @IBOutlet weak var detailStar: UIButton!
    @IBOutlet weak var detailTitle: UILabel!
    @IBOutlet weak var detailLocation: UILabel!
    @IBOutlet weak var detailCategory: UILabel!
    @IBOutlet weak var pageControl: UIPageControl!
    var data: Datum?
    var imageStrings: [String] = []
    lazy var currStar: Bool = {
        return UserDefaults.standard.bool(forKey: "\(String(describing: data?.id))")
    }()
    @IBAction func backButtonAction(_ sender: Any) {
        navigationController?.popViewController(animated: true)
    }
    @IBAction func starAction(_ sender: Any) {
        currStar.toggle()
        if currStar {
            addFavorite(playlandId: data?.id ?? 0) { [weak self] in
                UserDefaults.standard.set(self?.currStar, forKey: "\(String(describing: self?.data?.id))")
                self?.checkStar()
            }
        } else {
            deleteFavorite(playlandId: data?.id ?? 0) { [weak self] in
                UserDefaults.standard.set(self?.currStar, forKey: "\(String(describing: self?.data?.id))")
                self?.checkStar()
            }
        }
    }
    
    private func checkStar() {
        DispatchQueue.main.async { [weak self] in
            if self?.currStar ?? false {
                self?.detailStar.setImage(UIImage(named: "icon_star_red"), for: .normal)
            } else {
                self?.detailStar.setImage(UIImage(named: "icon_star_gray"), for: .normal)
            }
        }
    }
    
    func setData(_ data: Datum?) {
        self.data = data
        let splitUrls = data?.imageURL.split(separator: ",")
        splitUrls?.forEach { [weak self] in
            self?.imageStrings.append(String($0))
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.titleView = UIImageView(image: #imageLiteral(resourceName: "icon_title_heart.png"))
        
        pageControl.numberOfPages = imageStrings.count
        
        if let title = data?.title {
            detailTitle.text = title
        }
        if let location = data?.position {
            detailLocation.text = location
        }
        if let category = data?.category {
            detailCategory.text = category
        }
        if let likeCnt = data?.likeCnt {
            detailCommentCount.text = "\(likeCnt)"
        }
        if let content = data?.content {
            detailContent.text = content
        }
        checkStar()
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
        } else if segue.identifier == "commentSegue" {
            if let viewController = segue.destination as? DetailComentViewController {
                viewController.playlandId = data?.id ?? 0
            }
        }
    }
    
    private func viewControllerAtIndex(_ index: Int) -> ImagePageViewController? {
        let storyboard = UIStoryboard(name: "ViewPager", bundle: nil)
        guard let imagePageViewController = storyboard.instantiateViewController(withIdentifier: "ImagePageViewController") as? ImagePageViewController, index < imageStrings.count else { return nil }
        
        imagePageViewController.pageIndex = index
        imagePageViewController.image = imageStrings[index]
        
        return imagePageViewController
    }
}

extension DetailMainViewController: UIPageViewControllerDelegate {
    func pageViewController(_ pageViewController: UIPageViewController, didFinishAnimating finished: Bool, previousViewControllers: [UIViewController], transitionCompleted completed: Bool) {
        guard let pageContentViewController = pageViewController.viewControllers?[0] as? ImagePageViewController else { return }
        pageControl.currentPage = pageContentViewController.pageIndex ?? 0
    }
}

extension DetailMainViewController: UIPageViewControllerDataSource {
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerBefore viewController: UIViewController) -> UIViewController? {
        guard let viewController = viewController as? ImagePageViewController, let index = viewController.pageIndex, index > 0 else { return nil }
        
        return viewControllerAtIndex(index - 1)
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerAfter viewController: UIViewController) -> UIViewController? {
        guard let viewController = viewController as? ImagePageViewController, let index = viewController.pageIndex, index < imageStrings.count - 1 else { return nil }
        
        return viewControllerAtIndex(index + 1)
    }
    
    func presentationCountForPageViewController(pageViewController: UIPageViewController) -> Int {
        return imageStrings.count
    }
    
    func presentationIndexForPageViewController(pageViewController: UIPageViewController) -> Int {
        return 0
    }
}
