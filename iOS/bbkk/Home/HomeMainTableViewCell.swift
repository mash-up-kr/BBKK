//
//  HomeMainTableViewCell.swift
//  bbkk
//
//  Created by 이재성 on 23/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit
import Kingfisher

class HomeMainTableViewCell: UITableViewCell {
    var data: Datum?
    @IBOutlet weak var mainImageView: UIImageView!
    @IBOutlet weak var mainTitle: UILabel!
    @IBOutlet weak var mainLocation: UILabel!
    @IBOutlet weak var mainCategory: UILabel!
    @IBOutlet weak var likeCount: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    func setData(_ data: Datum?) {
        self.data = data
        
        let imageStrings = data?.imageURL.split(separator: ",")
        if let firstImage = imageStrings?.first, let url = URL(string: String(firstImage)) {
            mainImageView.kf.setImage(with: url)
        }
        if let title = data?.title {
            mainTitle.text = title
        }
        if let location = data?.position {
            mainLocation.text = location
        }
        if let category = data?.category {
            mainCategory.text = category
        }
        if let likeCnt = data?.likeCnt {
            likeCount.text = "\(likeCnt)"
        }
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

}
