package model

data class GameResult(private var win: Int = 0, private var lose: Int = 0, private var draw: Int = 0) {
    fun update(gameResultState: GameResultState) {
        when (gameResultState) {
            GameResultState.WIN -> lose++
            GameResultState.LOSE -> win++
            GameResultState.DRAW -> draw++
        }
    }

    override fun toString(): String {
        return "${win}승 ${lose}패 ${draw}무"
    }
}
