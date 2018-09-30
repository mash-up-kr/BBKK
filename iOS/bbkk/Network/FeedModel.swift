//
//  FeedModel.swift
//  bbkk
//
//  Created by 이재성 on 25/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import Foundation

class JSONNull: Codable {
    public init() {}
    
    public required init(from decoder: Decoder) throws {
        let container = try decoder.singleValueContainer()
        if !container.decodeNil() {
            throw DecodingError.typeMismatch(JSONNull.self, DecodingError.Context(codingPath: decoder.codingPath, debugDescription: "Wrong type for JSONNull"))
        }
    }
    
    public func encode(to encoder: Encoder) throws {
        var container = encoder.singleValueContainer()
        try container.encodeNil()
    }
}

struct FeedModel: Codable {
    let code: Int
    let msg: String
    let result: FeedResult
}

struct FeedResult: Codable {
    let nextCursor, totalSize: Int
    let popularData: JSONNull?
    let data: [Datum]

    enum CodingKeys: String, CodingKey {
        case nextCursor = "next_cursor"
        case totalSize = "total_size"
        case popularData = "popular_data"
        case data
    }
}

struct Datum: Codable {
    let id: Int
    let title, category, content, position: String
    let season: String
    let traveler: Traveler
    let imageURL: String
    let likeCnt: Int
    let createdAt: String

    enum CodingKeys: String, CodingKey {
        case id, title, category, season, traveler, content, position
        case imageURL = "image_url"
        case likeCnt = "review_cnt"
        case createdAt = "created_at"
    }
}

struct Traveler: Codable {
    let id: Int
    let nickname: String
    let property: String
}

extension FeedModel {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(FeedModel.self, from: data) else { return nil }
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

extension FeedResult {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(FeedResult.self, from: data) else { return nil }
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

extension Datum {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(Datum.self, from: data) else { return nil }
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

extension Traveler {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(Traveler.self, from: data) else { return nil }
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
