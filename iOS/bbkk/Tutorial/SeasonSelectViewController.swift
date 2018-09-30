//
//  SeasonSelectViewController.swift
//  bbkk
//
//  Created by 이재성 on 30/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit

private let reuseIdentifier = "SeasonSelectCollectionViewCell"

class SeasonSelectViewController: UIViewController {
    @IBOutlet weak var button: UIButton!
    @IBOutlet weak var mainTitle: UILabel!
    @IBOutlet weak var subTitle: UILabel!
    @IBOutlet weak var seasonLabel: UILabel!
    @IBOutlet weak var collectionView: UICollectionView!
    @IBAction func buttonAction(_ sender: Any) {
        UserDefaults.standard.set(seasons[curIndex], forKey: "seasons")
        if isTutorial {
            registerTravel { [weak self] in
                self?.dismiss(animated: true, completion: nil)
            }
        } else {
            dismiss(animated: true, completion: nil)
        }
    }
    @IBOutlet weak var gifImageView: UIImageView!
    var curIndex: Int = 0
    var isTutorial: Bool = true
    var typeString: String = "FOODFIGHTER"
    override func viewDidLoad() {
        super.viewDidLoad()
        
        typeString =  UserDefaults.standard.string(forKey: "typeEnum") ?? "FOODFIGHTER"
        collectionView.collectionViewLayout = .horizontalLayout2(width: view.bounds.width, height: view.bounds.height - 186)
        collectionView.contentInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right:0)
        
        if !isTutorial {
            button.setTitle("계절 선택 완료", for: .normal)
        }
        if typeString == typeEnum[0] {
            let gif = UIImage(gifName: "food.gif")
            gifImageView.setGifImage(gif)
            gifImageView.loopCount = -1
        } else if typeString == typeEnum[1] {
            let gif =  UIImage(gifName: "artist.gif")
            gifImageView.setGifImage(gif)
            gifImageView.loopCount = -1
        } else if typeString == typeEnum[2] {
            let gif =  UIImage(gifName: "travel.gif")
            gifImageView.setGifImage(gif)
            gifImageView.loopCount = -1
        } else if typeString == typeEnum[3] {
            let gif =  UIImage(gifName: "adventure.gif")
            gifImageView.setGifImage(gif)
            gifImageView.loopCount = -1
        } else {
            let gif =  UIImage(gifName: "miser.gif")
            gifImageView.setGifImage(gif)
            gifImageView.loopCount = -1
        }
        
        changeIndex(0)
    }
    
    private func changeIndex(_ index: Int) {
        curIndex = index
        seasonLabel.alpha = 0
        mainTitle.alpha = 0
        subTitle.alpha = 0
        UIView.animate(withDuration: 0.7, delay: 0, options: .curveEaseInOut, animations: { [weak self] in
            if index == 0 {
                self?.seasonLabel.text = "봄"
                self?.mainTitle.text = "나는 봄의 서울을 보고 싶어"
                self?.subTitle.text = "봄꽃이 가득한 서울의 봄을 여행할 거야"
            } else if index == 1 {
                self?.seasonLabel.text = "여름"
                self?.mainTitle.text = "나는 여름의 서울을 보고 싶어"
                self?.subTitle.text = "생기 넘치는 청량한 서울을 여행할 거야"
            } else if index == 2 {
                self?.seasonLabel.text = "가을"
                self?.mainTitle.text = "나는 가을의 서울을 보고 싶어"
                self?.subTitle.text = "단풍 가득한 서울의 거리를 거닐 거야"
            } else {
                self?.seasonLabel.text = "겨울"
                self?.mainTitle.text = "나는 겨울의 서울을 보고 싶어"
                self?.subTitle.text = "춥지만 마음은 따뜻한 서울을 즐길 거야"
            }
            self?.seasonLabel.alpha = 1
            self?.mainTitle.alpha = 1
            self?.subTitle.alpha = 1
        })
    }
}

extension SeasonSelectViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return seasons.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as? SeasonSelectCollectionViewCell else { return UICollectionViewCell() }
        if indexPath.row == 0 {
            cell.imageView.image = #imageLiteral(resourceName: "s_1x.png")
        } else if indexPath.row == 1 {
            cell.imageView.image = #imageLiteral(resourceName: "summer_1x.png")
        } else if indexPath.row == 2 {
            cell.imageView.image = #imageLiteral(resourceName: "a_1x.png")
        } else {
            cell.imageView.image = #imageLiteral(resourceName: "w_1x.png")
        }
        return cell
    }
}

extension SeasonSelectViewController: UICollectionViewDelegate {
    func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
        targetContentOffset.pointee = scrollView.contentOffset
        var factor: CGFloat = 0.5
        if velocity.x < 0 { factor = -factor }
        let indexPoint = scrollView.contentOffset.x / UIScreen.main.bounds.width
        let index = Int( round(indexPoint + factor) )
        if index < 0 {
            return
        }
        if index < seasons.count {
            changeIndex(index)
            collectionView.scrollToItem(at: IndexPath(row: Int(index), section: 0), at: .left, animated: true)
        }
    }
}

extension UICollectionViewLayout {
    static func horizontalLayout2(width: CGFloat, height: CGFloat) -> UICollectionViewLayout {
        let flow = UICollectionViewFlowLayout()
        flow.scrollDirection = .horizontal
        flow.sectionInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
        flow.itemSize = CGSize(width: width, height: height)
        flow.minimumInteritemSpacing = 0
        flow.minimumLineSpacing = 0
        return flow
    }
}
