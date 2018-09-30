//
//  checkConfirmModel.swift
//  bbkk
//
//  Created by 이재성 on 27/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import Foundation

struct CheckConfirmModel: Codable {
    let code: Int
    let msg: String
    let result: CheckConfirmResult
}

struct CheckConfirmResult: Codable {
    let id: Int
    let nickname, property: String
}

extension CheckConfirmModel {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(CheckConfirmModel.self, from: data) else { return nil }
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

extension CheckConfirmResult {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(CheckConfirmResult.self, from: data) else { return nil }
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
