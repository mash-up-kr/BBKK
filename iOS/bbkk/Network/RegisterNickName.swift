//
//  registerNickName.swift
//  bbkk
//
//  Created by 이재성 on 27/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import Foundation

struct RegisterNickName: Codable {
    let code: Int
    let msg: String
    let result: NicknameResult
}

struct NicknameResult: Codable {
    let nickname: String
}

extension RegisterNickName {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(RegisterNickName.self, from: data) else { return nil }
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

extension NicknameResult {
    init?(data: Data) {
        guard let me = try? JSONDecoder().decode(NicknameResult.self, from: data) else { return nil }
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
