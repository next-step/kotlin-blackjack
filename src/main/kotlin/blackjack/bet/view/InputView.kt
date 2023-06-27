package blackjack.bet.view

interface InputView {
    fun chargeWallet(name: String): Int
    fun getPlayers(): List<String>
    fun wantToHit(name: String): Boolean
}
