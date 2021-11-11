package blackject.model

/**
 * 참가자 정보 관리 클래스
 * */
data class Person(
    val name: String,
    val cardList: List<String>? = null
)
