//
//  DetailCommentWriteViewController.swift
//  bbkk
//
//  Created by 이재성 on 30/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit

class DetailCommentWriteViewController: UIViewController {
    var playlandId = 0
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var textField: UITextView!
    @IBAction func writeButton(_ sender: Any) {
        let content = textField.text
        let travelId = UserDefaults.standard.integer(forKey: "travelId")
        
        writeComment(content: content ?? "", travelId: travelId, playlandId: playlandId) { [weak self] in
            DispatchQueue.main.async {
                self?.navigationController?.popViewController(animated: true)
            }
        }
    }
    
    @IBAction func backButton(_ sender: Any) {
        navigationController?.popViewController(animated: true)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        nameLabel.text = UserDefaults.standard.string(forKey: "naming")
    }
}
