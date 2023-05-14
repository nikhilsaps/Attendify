package com.sinnikhy.attendify

class StudDataModel {
    var rollno :String="100"
    var name :String="name"
    var dob :String="dob"
    var course :String= "BCA"
    var sem:String= "1st"

    constructor(rollno:String,name:String,dob:String,course:String,sem:String){
        this.rollno=rollno
        this.name=name
        this.dob=dob
        this.course=course
        this.sem=sem
    }
    constructor(){

    }
}