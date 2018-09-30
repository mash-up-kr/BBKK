//
//  HomeMainViewController.swift
//  bbkk
//
//  Created by 이재성 on 23/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit
import YPImagePicker

class HomeMainViewController: UIViewController {
    @IBOutlet weak var seasonLabel: UILabel!
    var result: FeedResult?
    var data: [Datum]?
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.titleView = UIImageView(image: #imageLiteral(resourceName: "icon_title_heart.png"))
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        checkConfirm(notFound: { [weak self] in
            if let tutorialViewController = self?.storyboard?.instantiateViewController(withIdentifier: "TutorialNavigationController") {
                self?.present(tutorialViewController, animated: true, completion: nil)
            }
        }) { userModel in
            UserDefaults.standard.set(userModel?.result.id, forKey: "travelId")
            UserDefaults.standard.set(userModel?.result.nickname, forKey: "naming")
            UserDefaults.standard.set(userModel?.result.property, forKey: "typeEnum")
        }
        
        let season = UserDefaults.standard.string(forKey: "seasons") ?? "SPRING"
        getFeed(seasons: season) { [weak self] model in
            self?.result = model?.result
            self?.data = model?.result.data
            DispatchQueue.main.async {
                self?.tableView.reloadData()
            }
        }
        
    }
    
    private func isHeaderCell(_ indexPath: IndexPath) -> Bool {
        return indexPath.row == 0
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "Mypage" {
            
        }
    }
    
    private func imagesToUIImage(_ items: [YPMediaItem]) -> [UIImage] {
        var images: [UIImage] = []
        for item in items {
            switch item {
            case .photo(let photo):
                images.append(photo.image)
            case .video(_): break
            }
        }
        
        return images
    }
    
    private func pickerConfiguration() -> YPImagePickerConfiguration {
        var config = YPImagePickerConfiguration()
        config.hidesStatusBar = false
        config.startOnScreen = .library
        config.showsFilters = false
        config.bottomMenuItemSelectedColour = #colorLiteral(red: 0.9607843137, green: 0.1568627451, blue: 0.1843137255, alpha: 1)
        config.shouldSaveNewPicturesToAlbum = false
        config.library.maxNumberOfItems = 5
        config.library.skipSelectionsGallery = true
        return config
    }
    
    private func presentPicker() {
        let picker = YPImagePicker(configuration: pickerConfiguration())
        picker.didFinishPicking { [unowned picker, weak self] items, cancelled in
            if cancelled {
                picker.dismiss(animated: true, completion: nil)
                return
            }
            
            let storyboard = UIStoryboard(name: "Edit", bundle: nil)
            if let editMainViewController = storyboard.instantiateViewController(withIdentifier: "EditMainViewController") as? EditMainViewController, let strongSelf = self {
                editMainViewController.items = strongSelf.imagesToUIImage(items)
                picker.pushViewController(editMainViewController, animated: true)
            }
        }
        
        present(picker, animated: true, completion: nil)
    }
    
    @IBAction func writePostButton(_ sender: Any) {
        presentPicker()
    }
}

extension HomeMainViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if isHeaderCell(indexPath) {
            if let cell = tableView.dequeueReusableCell(withIdentifier: "HomeMainHeaderTableViewCell", for: indexPath) as? HomeMainHeaderTableViewCell {
                cell.setCount(data?.count)
                return cell
            }
        } else {
            if let cell = tableView.dequeueReusableCell(withIdentifier: "HomeMainTableViewCell", for: indexPath) as? HomeMainTableViewCell {
                cell.setData(data?[indexPath.row - 1])
                return cell
            }
        }
        
        return UITableViewCell()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (data?.count ?? 0) + 1
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let storyboard = UIStoryboard(name: "Detail", bundle: nil)
        if let detailMainViewController = storyboard.instantiateViewController(withIdentifier: "DetailMainViewController") as? DetailMainViewController, let cell = tableView.cellForRow(at: indexPath) as? HomeMainTableViewCell {
            detailMainViewController.setData(cell.data)
            navigationController?.pushViewController(detailMainViewController, animated: true)
        }
    }
    
    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        return UIView()
    }
}
