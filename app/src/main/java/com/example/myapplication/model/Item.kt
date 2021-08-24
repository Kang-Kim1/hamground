package com.example.myapplication.model

class Item {
    var _id  : String
    var _name : String
    var _link : String
    var _image : String
    var _lprice : Int
    var _hprice : Int
    var _mall  : String
    var _brand  : String
    var _maker  : String
    var _category1  : String
    var _category2  : String
    var _category3  : String
    var _category4  : String

    constructor(
        _id: String,
        _name: String,
        _link: String,
        _image: String,
        _lprice: Int,
        _hprice: Int,
        _mall: String,
        _brand: String,
        _maker: String,
        _category1: String,
        _category2: String,
        _category3: String,
        _category4: String
    ) {
        this._id = _id
        this._name = _name
        this._link = _link
        this._image = _image
        this._lprice = _lprice
        this._hprice = _hprice
        this._mall = _mall
        this._brand = _brand
        this._maker = _maker
        this._category1 = _category1
        this._category2 = _category2
        this._category3 = _category3
        this._category4 = _category4
    }
}