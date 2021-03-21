package domain

data class Score(private val value: Int) {

    init {
        require(value >= 0) { "점수는 0보다 커아합니다." }
    }
}
