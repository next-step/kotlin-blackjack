package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player

class GameView(
    private val io: IO,
    private val dealer: Dealer,
    private val players: List<Player>,
) {

    fun run() {
        players.forEach(::play)
        playDealer()
        io.print("")
    }

    private tailrec fun play(player: Player) {
        if (!player.canDrawCard) {
            return
        }

        io.print("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val shouldGiveCard = getDrawChoice()
        if (shouldGiveCard) {
            dealer.giveCard(player)
            io.print(player.text())
            play(player)
        }
    }

    private tailrec fun getDrawChoice(): Boolean =
        when (io.read().lowercase().trim()) {
            "y" -> true
            "n" -> false
            else -> {
                io.print("잘못된 입력입니다. 다시 입력해주세요.")
                getDrawChoice()
            }
        }

    private tailrec fun playDealer() {
        if (!dealer.shouldDraw) {
            return
        }

        dealer.drawSelf()
        io.print("딜러는 ${Dealer.DRAW_THRESHOLD}이하라 한장의 카드를 더 받았습니다.")
        playDealer()
    }
}
