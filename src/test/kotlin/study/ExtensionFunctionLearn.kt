package study

import io.kotest.core.spec.style.StringSpec

class ExtensionFunctionLearn : StringSpec({

    "ExtensionFunction(확장함수) 를 학습한다" {
        val countWords = "확장함수는 고랭의 리시버와 비슷합니다".countWords()
        print(countWords)
        print("참고 : https://kotlinlang.org/docs/extensions.html")
    }

    "확장 함수의 선언" {
        """
        - Kotlin은 클래스에서 상속하거나 Decorator 와 같은 디자인 패턴을 사용하지 않고도 
        - 새로운 기능으로 클래스나 인터페이스를 확장할 수 있는 기능을 제공
        - String 클래스나, List 같은 외부 클래스나 API 들에도 기능을 확장할수있다
        - 특별한 선언기법 "extensions" 을 사용 해야함
        
        fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        }
        """.trimIndent()
        val howToCall = "fun @기존클래스.@확장함수명 (@입력인자) { ... }"
        print("확장함수 선언하는법 : $howToCall")
    }

    "확장함수는 정적으로 처리되며, 기존클래스를 바꾸지 않습니다" {
        """
       - Extensions 는 정적으로 처리된다 : resolved statically
       - Extensions 기존클래스를 바꾸지 않는다 : do not actually modify the classes they extend
        """.trimIndent()
    }

    "확장 함수가 이름은 같지만 signature 가 다른 멤버 함수를 오버로드하는 것도 가능" {
        println("길이가11인문자열!!".length("2진수"))
        println("길이가11인문자열!!".length("10진수"))
        println("길이가11인문자열!!".length("16진수"))
    }

    "널가능 리시버 / Nullable receiver" {
    }

    "확장 프로그램을 멤버로 선언" {

        Connection(Host("kotl.in"), 443).connect()
        // Host("kotl.in").printConnectionString()  // error, the extension function is unavailable outside Connection
        println("Host(\"kotl.in\").printConnectionString() 는 에러가 나지만, Connection 객체 내부에서는 호출이 가능하다")

        """
        - 다른 클래스 내에서 한 클래스에 대한 확장을 선언할 수 있습니다. 
        - 이러한 확장 내에는 여러 한정자(=암시적 수신자) 없이 멤버에 액세스할 수 있는 객체가 있습니다.
        - 확장이 선언된 클래스의 인스턴스를 디스패치 리시버 라고 함
        - 확장 메서드의 리시버 유형 인스턴스를 확장 리시버 라고 합니다 .
        """.trimIndent()
    }
})

fun String.countWords(): Int {
    return this.split("\\s+".toRegex()).size
}

fun String.length(formatNotation: String): String {
    if (formatNotation == "10진수") {
        return (this.length).toString()
    } else if (formatNotation == "2진수") {
        return Integer.toBinaryString(this.length)
    } else { // 기본값은 16진수
        return Integer.toHexString(this.length)
    }
}

class Host(val hostname: String) {
    fun printHostname() {
        print(hostname)
    }
}

class Connection(val host: Host, val port: Int) {
    fun printPort() {
        print(port)
    }

    fun Host.printConnectionString() {
        printHostname() // calls Host.printHostname()
        print(":")
        printPort() // calls Connection.printPort()
    }

    fun connect() {
        /*...*/
        host.printConnectionString() // calls the extension function
    }
}
