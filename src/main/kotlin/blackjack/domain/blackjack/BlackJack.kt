package blackjack.domain.blackjack

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJack(
    private val dealer: Dealer = Dealer(),
    private val players: Players,
) {
    val hittablePlayers get() = players.hittablePlayers()

    init {
        players.addBaseCards(dealer, BASE_CARD_COUNT)
        dealer.addBaseCards(BASE_CARD_COUNT)
    }

    fun isEnd(): Boolean {
        return players.isEnd() && dealer.isEnd()
    }

    fun play(player: Player) {
        require(player in players) { "존재하지 않는 참가자입니다" }

        dealer.play(player)
    }

    fun playDealer() {
        dealer.addCard()
    }

    fun profits(): ParticipantProfits {
        check(isEnd()) { "게임이 종료되어야 수익 금액을 확인할 수 있습니다" }

        return ParticipantProfits.of(players, dealer)
    }

    companion object {
        const val BASE_CARD_COUNT = 2
    }
}
