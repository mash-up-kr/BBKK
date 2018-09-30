//
//  Service.swift
//  bbkk
//
//  Created by 이재성 on 25/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import Foundation
import Moya
import FCUUID

enum Service {
    case getNickName()
    case playlandFeed(cursor: Int, size: Int, rankFlag: Bool, rankDataSize: Int, Season: String)
    case playlandRegister(traveler_id: Int, title: String, content: String, category: String, season: String, position: String, images: String)
    case postReview(traveler_id: Int, playland_id: Int, review: String)
    case checkConfirm()
    case playlandComment(playLand_id: Int, cursor: Int, size: Int, rank_flag: Bool, rank_data_size: Int)
    case travelRegister()
    case addFavorite(playLand_id: Int)
    case deleteFavorite(playLand_id: Int)
    case writeComment(playland_id: Int, content: String, traveler_id: Int)
}

extension Service: TargetType {
    var baseURL: URL { return URL(string: "http://106.10.50.31:8080")! }
    var uuid: String { return FCUUID.uuidForDevice() }
    var path: String {
        switch self {
        case .getNickName:
            return "/v1/generate/nickname"
        case .playlandFeed:
            return "/v1/playland/feed"
        case .playlandRegister:
            return "/v1/playland/register"
        case .postReview:
            return "/v1/react/review/playland"
        case .checkConfirm:
            return "/v1/traveler/confirm"
        case .playlandComment(let playLand_id, _, _, _, _):
            return "/v1/react/review/feed/\(playLand_id)"
        case .travelRegister:
            return "/v1/traveler/register"
        case .addFavorite:
            return "/v1/bookmark"
        case .deleteFavorite:
            return "/v1/bookmark"
        case .writeComment:
            return "/v1/react/review/playland"
        }
    }
    var method: Moya.Method {
        switch self {
        case .getNickName,.playlandFeed,.checkConfirm,.playlandComment:
            return .get
        case .playlandRegister,.postReview,.travelRegister,.addFavorite,.writeComment:
            return .post
        case .deleteFavorite:
            return .delete
        }
    }
    var task: Task {
        switch self {
        case .writeComment(let playland_id, let content, let traveler_id):
            return .requestParameters(parameters: ["traveler_id": traveler_id,
                                                   "playland_id": playland_id,
                                                   "content": content], encoding: JSONEncoding.default)
        case .deleteFavorite(let playLand_id):
            return .requestParameters(parameters: ["traveler_id": UserDefaults.standard.integer(forKey: "travelId"),
                                                   "playland_id": playLand_id], encoding: JSONEncoding.default)
        case .addFavorite(let playLand_id):
            return .requestParameters(parameters: ["traveler_id": UserDefaults.standard.integer(forKey: "travelId"),
                                                   "playland_id": playLand_id], encoding: JSONEncoding.default)
        case .getNickName():
            return .requestPlain
        case .playlandFeed(let cursor, let size, let rankFlag, let rankDataSize, let Season):
            return .requestParameters(parameters: ["cursor": cursor,
                                                   "size": size,
                                                   "rank_flag": "false",
                                                   "rank_data_size": rankDataSize], encoding: URLEncoding.queryString)
        case .playlandRegister(let travelerId, let title, let content, let category, let season, let position, let images):
            return .requestParameters(parameters: ["traveler_id": travelerId,
                                                   "title": title,
                                                   "content": content,
                                                   "category": category,
                                                   "season": season,
                                                   "position": position,
                                                   "images": images
                ], encoding: JSONEncoding.default)
        case .postReview(let travelerId, let playlandId, review: let review):
            return .requestParameters(parameters: ["traveler_id": travelerId,
                                                   "playland_id": playlandId,
                                                   "review": review], encoding: JSONEncoding.default)
        case .checkConfirm():
            return .requestPlain
        case .playlandComment(_, let cursor, let size, let rank_flag, let rank_data_size):
            return .requestParameters(parameters: ["cursor": cursor,
                                                   "size": size,
                                                   "rank_flag": "true",
                                                   "rank_data_size": rank_data_size], encoding: URLEncoding.queryString)
        case .travelRegister():
            return .requestParameters(parameters: ["nickname": UserDefaults.standard.string(forKey: "naming") ?? "",
                                                   "property": UserDefaults.standard.string(forKey: "typeEnum") ?? ""], encoding: JSONEncoding.default)
        }
    }
    var sampleData: Data {
        return "Half measures are as bad as nothing at all.".utf8Encoded
    }
    var headers: [String: String]? {
        return ["Content-type": "application/json",
                "uuid": uuid]
    }
}

private extension String {
    var urlEscaped: String {
        return addingPercentEncoding(withAllowedCharacters: .urlPathAllowed)!
    }
    
    var utf8Encoded: Data {
        return data(using: .utf8)!
    }
}
