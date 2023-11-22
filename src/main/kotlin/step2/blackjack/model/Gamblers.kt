package step2.blackjack.model

/**
 * 유저 리스트
 * */
data class Gamblers(private val gambler: List<Gambler>): List<Gambler> by gambler {

    init {
        require(gambler.isNotEmpty()) {
            "겜블러들은 비어 있지 않아야 한다."
        }

        require(gambler.size in MIN_SIZE..MAX_SIZE) {
            "겜블러들의 수는 ${MIN_SIZE}부터 ${MAX_SIZE}까지 여야합니다."
        }
    }

    companion object {

        private const val MIN_SIZE = 1
        private const val MAX_SIZE = 10

        fun from(names: Names): Gamblers = Gamblers(names.toList().map { name -> Gambler.from(name) })

    }
}
