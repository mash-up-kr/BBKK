//
//  FeedService.swift
//  bbkk
//
//  Created by 이재성 on 25/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import Foundation
import Moya

let provider = MoyaProvider<Service>()

func writeComment(content: String, travelId: Int, playlandId: Int, complete: @escaping (() -> Void)) {
    provider.request(.writeComment(playland_id: playlandId, content: content, traveler_id: travelId), callbackQueue: DispatchQueue.global(), progress: nil) { result in
        switch result {
        case let .success(moyaResponse):
            let data = moyaResponse.data
            let statusCode = moyaResponse.statusCode
            if statusCode == 200 {
                complete()
            } else {
                print(data)
            }
            
        case let .failure(error):
            print(error)
        }
    }
}

func deleteFavorite(playlandId: Int, complete: @escaping (() -> Void)) {
    provider.request(.deleteFavorite(playLand_id: playlandId), callbackQueue: DispatchQueue.global(), progress: nil) { result in
        switch result {
        case let .success(moyaResponse):
            let data = moyaResponse.data
            let statusCode = moyaResponse.statusCode
            if statusCode == 204 {
                complete()
            } else {
                print(data)
            }
            
        case let .failure(error):
            print(error)
        }
    }
}

func addFavorite(playlandId: Int, complete: @escaping (() -> Void)) {
    provider.request(.addFavorite(playLand_id: playlandId), callbackQueue: DispatchQueue.global(), progress: nil) { result in
        switch result {
        case let .success(moyaResponse):
            let data = moyaResponse.data
            let statusCode = moyaResponse.statusCode
            if statusCode == 200 {
                complete()
            } else {
                print(data)
            }
            
        case let .failure(error):
            print(error)
        }
    }
}

func registerTravel(complete: @escaping (() -> Void)) {
    provider.request(.travelRegister(), callbackQueue: DispatchQueue.global(), progress: nil) { result in
        switch result {
        case let .success(moyaResponse):
            let data = moyaResponse.data
            let statusCode = moyaResponse.statusCode
            if statusCode == 200 {
                complete()
            } else {
                print(data)
            }
            
        case let .failure(error):
            print(error)
        }
    }
}

func checkConfirm(notFound: @escaping (() -> Void), conflict: @escaping ((CheckConfirmModel?) -> Void)) {
    provider.request(.checkConfirm(), callbackQueue: DispatchQueue.global(), progress: nil) { result in
        switch result {
        case let .success(moyaResponse):
            let data = moyaResponse.data
            let statusCode = moyaResponse.statusCode
            if statusCode == 200 {
                let model = CheckConfirmModel.init(data: data)
                if model?.code == 409 {
                    conflict(model)
                } else {
                    notFound()
                }
            } else {
                print(data)
            }
            
        case let .failure(error):
            print(error)
        }
    }
}

func getNickname(complete: @escaping ((RegisterNickName?) -> Void)) {
    provider.request(.getNickName(), callbackQueue: DispatchQueue.global(), progress: nil) { result in
        switch result {
        case let .success(moyaResponse):
            let data = moyaResponse.data
            let statusCode = moyaResponse.statusCode
            if statusCode == 200 {
                let model = RegisterNickName.init(data: data)
                complete(model)
            } else {
                print(data)
            }
            
        case let .failure(error):
            print(error)
        }
    }
}

func getComment(playLand_id: Int, cursor: Int, size: Int, rank_flag: Bool, rank_data_size: Int, complete: @escaping ((GetCommentModel?) -> Void)) {
    provider.request(.playlandComment(playLand_id: playLand_id, cursor: cursor, size: size, rank_flag: rank_flag, rank_data_size: rank_data_size), callbackQueue: DispatchQueue.global(), progress: nil) { result in
        switch result {
        case let .success(moyaResponse):
            let data = moyaResponse.data
            let statusCode = moyaResponse.statusCode
            if statusCode == 200 {
                let model = GetCommentModel.init(data: data)
                complete(model)
            } else {
                print(data)
            }
            
        case let .failure(error):
            print(error)
        }
    }
}

func postFeedRegister(title: String, content: String, category: String, season: String, position: String, images: String, success: @escaping (() -> Void)) {
    provider.request(.playlandRegister(traveler_id: UserDefaults.standard.integer(forKey: "travelId") , title: title, content: content, category: category, season: season, position: position, images: images), callbackQueue: DispatchQueue.global(), progress: nil) { result in
        switch result {
        case let .success(moyaResponse):
            let data = moyaResponse.data
            let statusCode = moyaResponse.statusCode
            if statusCode == 204 {
                success()
            } else {
                print(data)
            }
            
        case let .failure(error):
            print(error)
        }
    }
}

func getFeed(seasons: String, success: @escaping ((FeedModel?) -> Void)) {
    provider.request(.playlandFeed(cursor: 0, size: 10, rankFlag: false, rankDataSize: 0, Season: seasons), callbackQueue: DispatchQueue.global(), progress: nil) { result in
        switch result {
        case let .success(moyaResponse):
            let data = moyaResponse.data
            let statusCode = moyaResponse.statusCode
            if statusCode == 200 {
                let model = FeedModel.init(data: data)
                success(model)
            } else {
                print(data)
            }
            
        case let .failure(error):
            print(error)
        }
    }
}
