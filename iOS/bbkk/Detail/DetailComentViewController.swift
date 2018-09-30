//
//  DetailComentViewController.swift
//  bbkk
//
//  Created by 이재성 on 25/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit

class DetailComentViewController: UIViewController {
    @IBOutlet weak var tableView: UITableView!
    var playlandId: Int = 0
    var model: [Review]?
    
    @IBAction func backButtonAction(_ sender: Any) {
        navigationController?.popViewController(animated: true)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.titleView = UIImageView(image: #imageLiteral(resourceName: "icon_title_heart.png"))
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        getComment(playLand_id: playlandId, cursor: 0, size: 100, rank_flag: true, rank_data_size: 5) { [weak self] model in
            self?.model = model?.result.review
            self?.tableView.reloadData()
        }
    }
    
    private func isHeaderCell(_ indexPath: IndexPath) -> Bool {
        return indexPath.row == 0
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "writeComment" {
            if let viewController = segue.destination as? DetailCommentWriteViewController {
                viewController.playlandId = playlandId
            }
        }
    }
}

extension DetailComentViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if isHeaderCell(indexPath) {
            if let cell = tableView.dequeueReusableCell(withIdentifier: "DetailComentHeaderTableViewCell", for: indexPath) as? DetailComentHeaderTableViewCell {
                return cell
            }
        } else {
            if let cell = tableView.dequeueReusableCell(withIdentifier: "DetailComentTableViewCell", for: indexPath) as? DetailComentTableViewCell {
                if let data = model?[indexPath.row - 1] {
                    cell.setData(name: data.nickname, content: data.content)
                }
                return cell
            }
        }
        
        return UITableViewCell()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (model?.count ?? 0) + 1
    }
    
    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        return UIView()
    }
}
