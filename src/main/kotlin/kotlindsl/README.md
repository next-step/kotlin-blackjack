# 🚀 1단계 - 코틀린 DSL

## 📝 요구사항
```kotlin
introduce {
  name("박재성")
  company("우아한형제들")
  skills {
    soft("A passion for problem solving")
    soft("Good communication skills")
    hard("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
```

## 적용해 볼 것

## 리뷰 받은 것

## 궁금한 것
- 실무에서는 어떻게 쓰이나

## 챗GPT 와 대화
### backing field
클래스 내부에서는 List를 가변하게 사용하고, 외부에서는 List를 불변하게 사용하고 싶다면, 아래와 같이 구현할 수 있습니다
```kotlin
class MyClass {
    private val _list = mutableListOf<Int>()

    val list: List<Int>
        get() = _list.toList()

    fun addItem(item: Int) {
        _list.add(item)
    }

    fun removeItem(item: Int) {
        _list.remove(item)
    }
}

fun main() {
    val myObj = MyClass()

    myObj.addItem(1)
    myObj.addItem(2)
    myObj.addItem(3)

    val myList = myObj.list
    // myList.add(4) // 이 줄을 주석 해제하면 컴파일 에러가 발생합니다.

    println(myList) // Output: [1, 2, 3]

    myObj.removeItem(2)

    println(myList) // Output: [1, 2, 3]
}
```
위 예제에서 MyClass는 내부적으로 가변 리스트인 _list를 유지합니다. 하지만 외부에 노출되는 list는 get() 메소드를 통해 _list.toList()의 불변 리스트로 반환합니다. 따라서, 외부에서는 list를 읽기 전용으로 사용할 수 있습니다. addItem() 및 removeItem() 메소드는 _list를 수정하기 위해 사용됩니다.

myObj.list로 접근할 때는 List이므로 수정이 불가능합니다. myList.add(4)와 같이 myList에 요소를 추가하려고 하면 컴파일 에러가 발생합니다. 그러나 myObj.addItem(4)와 같이 MyClass의 addItem() 메소드를 사용하여 리스트를 수정할 수 있습니다.

따라서 클래스 내부에서는 가변 리스트 _list가 유지되고, 외부에서는 불변 리스트 list를 통해 클래스의 리스트를 읽기 전용으로 사용할 수 있습니다.
