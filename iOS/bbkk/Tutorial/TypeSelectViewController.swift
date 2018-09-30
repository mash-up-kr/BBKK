//
//  TypeSelectViewController.swift
//  bbkk
//
//  Created by 이재성 on 30/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit
import SwiftyGif

private let reuseIdentifier = "TypeSelectCollectionViewCell"
let typeEnum: [String] = ["FOODFIGHTER", "ARTIST", "VISITOR", "EXPLORER", "BEGGER"]
let seasons: [String] = ["SPRING", "SUMMER", "AUTUMN", "WINTER"]

class TypeSelectViewController: UIViewController {
    @IBOutlet weak var titleLabel: UILabel!
    private let collectionViewHeight: CGFloat = 171
    private let collectionViewCellMargin: CGFloat = 98
    var curIndex: Int = 0
    var isTutorial: Bool = true
    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var typeLabel: UILabel!
    @IBOutlet weak var detailLabel: UILabel!
    @IBOutlet weak var typeAcceptButton: UIButton!
    @IBAction func buttonAction(_ sender: Any) {
        UserDefaults.standard.set(typeEnum[curIndex], forKey: "typeEnum")
        if isTutorial {
            if let viewController = self.storyboard?.instantiateViewController(withIdentifier: "SeasonSelectViewController") as? SeasonSelectViewController {
                navigationController?.pushViewController(viewController, animated: true)
            }
        } else {
            dismiss(animated: true, completion: nil)
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        collectionView.collectionViewLayout = .horizontalLayout(width: collectionViewCellMargin, height: collectionViewHeight)
        collectionView.contentInset = UIEdgeInsets(top: 141, left: (UIScreen.main.bounds.width / 2) - (collectionViewCellMargin / 2), bottom: 0, right: (UIScreen.main.bounds.width / 2) - (collectionViewCellMargin / 2))
        let name = UserDefaults.standard.string(forKey: "naming") ?? ""
        titleLabel.text = """
        \(name)님의
        여행 타입은?
        """
        changeIndex(0)
    }
    
    private func changeIndex(_ index: Int) {
        curIndex = index
        typeLabel.alpha = 0
        detailLabel.alpha = 0
        UIView.animate(withDuration: 0.7, delay: 0, options: .curveEaseInOut, animations: { [weak self] in
            if index == 0 {
                self?.typeLabel.text = "식도락형"
                self?.detailLabel.text = """
                뭐든지 먹어버려!
                관광지의 모든 음식을 맛보고 말 거야!
                내 입맛을 만족시키기는 쉽지 않을 거야
                """
            } else if index == 1 {
                self?.typeLabel.text = "예술가형"
                self?.detailLabel.text = """
                아름다운 서울의 경치를 내 두 눈에 담겠어!
                예쁜 사진은 내 몫이야 남는 건 사진뿐이라고!
                
                """
            } else if index == 2 {
                self?.typeLabel.text = "관광객"
                self?.detailLabel.text = """
                서울에 가장 유명한 곳은 어디지?
                명소 도장 깨기 쾅쾅!
                남들 가본 곳은 나도 꼭 가봐야 해!
                """
            } else if index == 3 {
                self?.typeLabel.text = "탐험가형"
                self?.detailLabel.text = """
                어디든 움직이고 보는 불도저 타입!
                눈이 오나 비가 오나 나는 움직일 거야!
                동에 번쩍 서에 번쩍 여행은 움직이는 거야!
                """
            } else {
                self?.typeLabel.text = "알뜰형"
                self?.detailLabel.text = """
                내 지갑은 소중하니까!
                한 푼도 허투루 쓰지 않겠어!
                돈을 많이 쓰지 않고서도 잘 놀 수 있다는 걸 보여줄게!
                """
            }
            self?.typeLabel.alpha = 1
            self?.detailLabel.alpha = 1
        })
    }
}

extension TypeSelectViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return typeEnum.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as? TypeSelectCollectionViewCell else { return UICollectionViewCell() }
        if indexPath.row == 0 {
            let gif = UIImage(gifName: "food.gif")
            cell.imageView.setGifImage(gif)
            cell.imageView.loopCount = -1
        } else if indexPath.row == 1 {
            let gif =  UIImage(gifName: "artist.gif")
            cell.imageView.setGifImage(gif)
            cell.imageView.loopCount = -1
        } else if indexPath.row == 2 {
            let gif =  UIImage(gifName: "travel.gif")
            cell.imageView.setGifImage(gif)
            cell.imageView.loopCount = -1
        } else if indexPath.row == 3 {
            let gif =  UIImage(gifName: "adventure.gif")
            cell.imageView.setGifImage(gif)
            cell.imageView.loopCount = -1
        } else {
            let gif =  UIImage(gifName: "miser.gif")
            cell.imageView.setGifImage(gif)
            cell.imageView.loopCount = -1
        }
        return cell
    }
}

extension TypeSelectViewController: UICollectionViewDelegate {
    func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
        targetContentOffset.pointee = scrollView.contentOffset
        var factor: CGFloat = 0.5
        if velocity.x < 0 { factor = -factor }
        let indexPoint = (scrollView.contentOffset.x + (UIScreen.main.bounds.width / 2) - (collectionViewCellMargin / 2)) / (UIScreen.main.bounds.width / 2)
        let index = Int( round(indexPoint + factor) )
        if index < 0 {
            return
        }
        if index < typeEnum.count {
            changeIndex(index)
            collectionView.scrollToItem(at: IndexPath(row: Int(index), section: 0), at: .left, animated: true)
        }
    }
}

extension UICollectionViewLayout {
    static func horizontalLayout(width: CGFloat, height: CGFloat) -> UICollectionViewLayout {
        let flow = UICollectionViewFlowLayout()
        flow.scrollDirection = .horizontal
        flow.sectionInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
        flow.itemSize = CGSize(width: width, height: height)
        flow.minimumInteritemSpacing = 0
        flow.minimumLineSpacing = (UIScreen.main.bounds.width / 2) - width
        return flow
    }
}
