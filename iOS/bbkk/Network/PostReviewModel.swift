//
//  postReviewModel.swift
//  bbkk
//
//  Created by 이재성 on 27/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import Foundation

struct PostReviewModel: Codable {
    let code: Int
    let msg: String
    let result: PostReviewResult
}

struct PostReviewResult: Codable {
    let nextCursor, totalSize: Int
    let popularReview, comment: [Comment]

    enum CodingKeys: String, CodingKey {
        case nextCursor = "next_cursor"
        case totalSize = "total_size"
        case popularReview = "popular_review"
        case comment
    }
}

struct Comment: Codable {
    let travelerID: Int
    let nickname, content: String
    let likeCnt: Int
    let likeStatus: Bool
    let reviewAt: String

    enum CodingKeys: String, CodingKey {
        case travelerID = "traveler_id"
        case nickname, content
        case likeCnt = "like_cnt"
        case likeStatus = "like_status"
        case reviewAt = "review_at"
    }
}

extension PostReviewModel {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(PostReviewModel.self, from: data) else { return nil }
        self = me
    }

    init?(_ json: String, using encoding: String.Encoding = .utf8) {
        guard let data = json.data(using: encoding) else { return nil }
        self.init(data: data)
    }

    init?(fromURL url: String) {
        guard let url = URL(string: url) else { return nil }
        guard let data = try? Data(contentsOf: url) else { return nil }
        self.init(data: data)
    }

    var jsonData: Data? {
        return try? JSONEncoder().encode(self)
    }

    var json: String? {
        guard let data = self.jsonData else { return nil }
        return String(data: data, encoding: .utf8)
    }
}

extension PostReviewResult {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(PostReviewResult.self, from: data) else { return nil }
        self = me
    }

    init?(_ json: String, using encoding: String.Encoding = .utf8) {
        guard let data = json.data(using: encoding) else { return nil }
        self.init(data: data)
    }

    init?(fromURL url: String) {
        guard let url = URL(string: url) else { return nil }
        guard let data = try? Data(contentsOf: url) else { return nil }
        self.init(data: data)
    }

    var jsonData: Data? {
        return try? JSONEncoder().encode(self)
    }

    var json: String? {
        guard let data = self.jsonData else { return nil }
        return String(data: data, encoding: .utf8)
    }
}

extension Comment {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(Comment.self, from: data) else { return nil }
        self = me
    }

    init?(_ json: String, using encoding: String.Encoding = .utf8) {
        guard let data = json.data(using: encoding) else { return nil }
        self.init(data: data)
    }

    init?(fromURL url: String) {
        guard let url = URL(string: url) else { return nil }
        guard let data = try? Data(contentsOf: url) else { return nil }
        self.init(data: data)
    }

    var jsonData: Data? {
        return try? JSONEncoder().encode(self)
    }

    var json: String? {
        guard let data = self.jsonData else { return nil }
        return String(data: data, encoding: .utf8)
    }
}
