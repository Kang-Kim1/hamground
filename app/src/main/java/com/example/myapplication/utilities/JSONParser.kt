package com.example.myapplication.utilities

import com.example.myapplication.model.Item
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class JSONParser {

    companion object {
        fun parseJSON(jsonData : String) : ArrayList<Item> {
            val itemList : ArrayList<Item> = ArrayList<Item>()

            try {
                val jsonObject : JSONObject = JSONObject(jsonData)
                val predictions : String = jsonObject.getString("result")
                val jsonArray : JSONArray = JSONArray(predictions)

                for(i in 0 until jsonArray.length()) {
                    val subJsonObject : JSONObject = jsonArray.getJSONObject(i);
                    val id : String = subJsonObject.getString("id");
                    val name : String = subJsonObject.getString("name") ;
                    val link : String = subJsonObject.getString("link");
                    val image : String = subJsonObject.getString("image");
                    val lprice : Int = Integer.parseInt(subJsonObject.getString("lprice"));
                    val hprice : Int = Integer.parseInt(subJsonObject.getString("hprice"));
                    val mall : String = subJsonObject.getString("mall");
                    val brand : String = subJsonObject.getString("brand");
                    val maker : String = subJsonObject.getString("maker") ;
                    val category1 : String = subJsonObject.getString("category1") ;
                    val category2 : String = subJsonObject.getString("category2") ;
                    val category3 : String = subJsonObject.getString("category3") ;
                    val category4 : String = subJsonObject.getString("category4") ;

                    val item : Item = Item(id, name, link, image, lprice, hprice, mall, brand, maker, category1, category2, category3, category4);
                    itemList.add(item)
                }
            } catch (ex : JSONException) {
                ex.printStackTrace()
            }
            System.out.println("LIST UPDATED")
            return itemList
        }
    }
}