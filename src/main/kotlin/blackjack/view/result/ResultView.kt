
package blackjack.view.result

import blackjack.domain.Players

interface ResultView {
    fun showCardsDelivered(players: Players)
}
