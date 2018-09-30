//
//  UIView+Extension.swift
//  bbkk
//
//  Created by 이재성 on 22/09/2018.
//  Copyright © 2018 mashup. All rights reserved.
//

import UIKit

@IBDesignable
extension UIView {
    @IBInspectable
    public var cornerRadius: CGFloat {
        set (radius) {
            self.layer.cornerRadius = radius
            self.layer.masksToBounds = radius > 0
        }
        
        get {
            return self.layer.cornerRadius
        }
    }
    
    @IBInspectable public var borderWidth: CGFloat {
        set (borderWidth) {
            self.layer.borderWidth = borderWidth
        }
        
        get {
            return self.layer.borderWidth
        }
    }
    
    @IBInspectable public var borderColor:UIColor? {
        set (color) {
            self.layer.borderColor = color?.cgColor
        }
        
        get {
            if let color = self.layer.borderColor
            {
                return UIColor(cgColor: color)
            } else {
                return nil
            }
        }
    }
}
