//
//  ImagePageViewController.swift
//  bbkk
//
//  Created by 이재성 on 24/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit
import Kingfisher

class ImagePageViewController: UIViewController {
    @IBOutlet weak var imageView: UIImageView!
    var pageIndex: Int?
    var image: String?
    var img: UIImage?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let image = image, let url = URL(string: image) {
            imageView.kf.setImage(with: url)
        } else {
            imageView.image = img
        }
    }
}
