package dsl.domain

sealed class Skill(private val description: String) {

    init {
        require(description.isNotBlank()) { DESCRIPTION_EMPTY_ERROR_MESSAGE }
    }

    companion object {
        private const val DESCRIPTION_EMPTY_ERROR_MESSAGE = "능력 설명이 비어있을 수 없습니다"
    }
}

class Soft(description: String) : Skill(description)

class Hard(description: String) : Skill(description)
