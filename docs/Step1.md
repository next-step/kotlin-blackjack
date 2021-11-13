# Step1
## 제시된 코드 
```
introduce {
  name("박재성")
  company("우아한형제들")
  skills {
    soft ("A passion for problem solving")
    soft ("Good communication skills")
    hard ("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
```

## 설계 

이 전체를 아우르는 Person 객체도 필요  

* [] Name
  * [] 문자열을 가진다.  
  * [] 공백으로만 이루어져있으면 안된다.  
* [] Company
  * [] 문자열을 가진다. 
  * [] 공백으로만 이루어져있으면 안된다.
* [] Skills 
  * [] Skill
    * [] soft/hard 로 구성되어 있다.
    * [] 문자열을 입력받고 생성된다 -> Sealed 클래스가 적합 판단(기존 만들어진게 아니므로) 
    * [] 공백으로만 이루어져있으면 안된다.
* [] Language 
    * [] Map<String, Int> 로 이루어졌다고 판단.    
    * [] 공백엔 널값이 들어오면 안된다고 판단.