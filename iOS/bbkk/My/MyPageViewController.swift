//
//  MyPageViewController.swift
//  bbkk
//
//  Created by 이재성 on 25/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit
import SafariServices

class MyPageViewController: UIViewController {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var typeImageView: UIImageView!
    @IBOutlet weak var backgroundImage: UIImageView!
    @IBOutlet weak var typeLabel: UILabel!
    @IBAction func closeButtonAction(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    @IBAction func highlightButton(_ sender: Any) {
        guard let url = URL(string: "http://korean.visitseoul.net/highlights-kr") else { return }
        let safariViewController = SFSafariViewController(url: url)
        present(safariViewController, animated: true, completion: nil)
        
    }
    
    @IBAction func travelSelect(_ sender: Any) {
        let mainStoryboard = UIStoryboard.init(name: "Main", bundle: nil)
        if let vc = mainStoryboard.instantiateViewController(withIdentifier: "TypeSelectViewController") as? TypeSelectViewController {
            vc.isTutorial = false
            present(vc, animated: true, completion: nil)
        }
    }
    @IBAction func seasonSelect(_ sender: Any) {
        let mainStoryboard = UIStoryboard.init(name: "Main", bundle: nil)
        if let vc = mainStoryboard.instantiateViewController(withIdentifier: "SeasonSelectViewController") as? SeasonSelectViewController {
            vc.isTutorial = false
            present(vc, animated: true, completion: nil)
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let name = UserDefaults.standard.string(forKey: "naming") ?? ""
        titleLabel.text = name
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        let typeString =  UserDefaults.standard.string(forKey: "typeEnum") ?? "FOODFIGHTER"
        let seasonString = UserDefaults.standard.string(forKey: "seasons") ?? "FOODFIGHTER"
        if typeString == typeEnum[0] {
            typeLabel.text = "식도락형"
            let gif = UIImage(gifName: "food.gif")
            typeImageView.setGifImage(gif)
            typeImageView.loopCount = -1
        } else if typeString == typeEnum[1] {
            typeLabel.text = "예술가형"
            let gif =  UIImage(gifName: "artist.gif")
            typeImageView.setGifImage(gif)
            typeImageView.loopCount = -1
        } else if typeString == typeEnum[2] {
            typeLabel.text = "관광객"
            let gif =  UIImage(gifName: "travel.gif")
            typeImageView.setGifImage(gif)
            typeImageView.loopCount = -1
        } else if typeString == typeEnum[3] {
            typeLabel.text = "탐험가형"
            let gif =  UIImage(gifName: "adventure.gif")
            typeImageView.setGifImage(gif)
            typeImageView.loopCount = -1
        } else {
            typeLabel.text = "알뜰형"
            let gif =  UIImage(gifName: "miser.gif")
            typeImageView.setGifImage(gif)
            typeImageView.loopCount = -1
        }
        
        if seasonString == seasons[0] {
            backgroundImage.image = #imageLiteral(resourceName: "bg_spring.png")
        } else if seasonString == seasons[1] {
            backgroundImage.image = #imageLiteral(resourceName: "bg_summer.png")
        } else if seasonString == seasons[2] {
            backgroundImage.image = #imageLiteral(resourceName: "bg_autumn.png")
        } else {
            backgroundImage.image = #imageLiteral(resourceName: "bg_spring_winter_1x.png")
        }
    }
}
