package com.example.mad_practical_10_22012012024

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ContactAdapter (context:Context,val array:ArrayList<Contect>):ArrayAdapter<Contect>(context,0,array){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        view.findViewById<TextView>(R.id.name).text=array.get(position).name
        view.findViewById<TextView>(R.id.address).text=array.get(position).address
        view.findViewById<TextView>(R.id.contect).text=array.get(position).phoneNo
        view.findViewById<TextView>(R.id.email).text=array.get(position).emailId
        return view
    }
}