//
//  GetCommentModel.swift
//  bbkk
//
//  Created by 이재성 on 29/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import Foundation

struct GetCommentModel: Codable {
    let code: Int
    let msg: String
    let result: CommentResult
}

struct CommentResult: Codable {
    let nextCursor, totalSize: Int
    let popularReview, review: [Review]

    enum CodingKeys: String, CodingKey {
        case nextCursor = "next_cursor"
        case totalSize = "total_size"
        case popularReview = "popular_review"
        case review
    }
}

struct Review: Codable {
    let id: Int
    let nickname, content: String
    let travelerID, likeCnt: Int
    let likeStatus: Bool
    let reviewAt: String

    enum CodingKeys: String, CodingKey {
        case id, nickname, content
        case travelerID = "traveler_id"
        case likeCnt = "like_cnt"
        case likeStatus = "like_status"
        case reviewAt = "review_at"
    }
}

// MARK: Convenience initializers

extension GetCommentModel {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(GetCommentModel.self, from: data) else { return nil }
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

extension CommentResult {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(CommentResult.self, from: data) else { return nil }
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

extension Review {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(Review.self, from: data) else { return nil }
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
