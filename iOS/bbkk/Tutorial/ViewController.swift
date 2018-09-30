//
//  ViewController.swift
//  bbkk
//
//  Created by 이재성 on 08/08/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var nickNameLabel: UILabel!
    
    func setNickName(_ text: String) {
        DispatchQueue.main.async { [weak self] in
            self?.nickNameLabel.alpha = 0
            UIView.animate(withDuration: 0.6, delay: 0, options: .curveEaseInOut, animations: {
                self?.nickNameLabel.alpha = 1
                self?.nickNameLabel.text = text
            })
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        getNickName()
    }
    
    private func getNickName() {
        getNickname { [weak self] nickName in
            if let nickName = nickName?.result.nickname {
                self?.setNickName(nickName)
            }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @IBAction func nickNameButton(_ sender: Any) {
        getNickName()
    }
    
    @IBAction func startButton(_ sender: Any) {
        if let name = nickNameLabel.text {
            UserDefaults.standard.set(name, forKey: "naming")
        }
    }
}
