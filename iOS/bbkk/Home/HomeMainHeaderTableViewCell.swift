//
//  HomeMainHeaderTableViewCell.swift
//  bbkk
//
//  Created by 이재성 on 23/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit

class HomeMainHeaderTableViewCell: UITableViewCell {
    var count: Int?
    @IBOutlet weak var dataCount: UILabel!
    @IBOutlet weak var seasonLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func setCount(_ count: Int?) {
        if let season = UserDefaults.standard.string(forKey: "seasons") {
            if season == seasons[0] {
                seasonLabel.text = "서울의 봄"
            } else if season == seasons[1] {
                seasonLabel.text = "서울의 여름"
            } else if season == seasons[2] {
                seasonLabel.text = "서울의 가을"
            } else {
                seasonLabel.text = "서울의 겨울"
            }
        }
            
        self.count = count
        dataCount.text = "\(count ?? 0)개"
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
