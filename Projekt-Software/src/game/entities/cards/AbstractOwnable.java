package game.entities.cards;

import game.boundaries.Outputable;
import game.entities.CardManager;
import game.entities.Player;

public abstract class AbstractOwnable extends AbstractCard{

	protected Player owner;
	
	
	public AbstractOwnable(CardManager cardManager, Outputable output, CardType cardType) {
		super(cardManager, output, cardType);
		owner = null;
	}
	
	public Player getOwner(){
		return owner; 
	}
	
	public void clearOwner(){
		output.removeOwner(cardManager.getCardNumber(this));
		owner = null;
	}
	
	/****************************************************************************
	 * Function can only be called by an inheriting class						*
	 * If Field has no owner. Player can buy the field if he has enough money	*
	 ***************************************************************************/

	@Override
	public String toString(){
		String str = super.toString();
		if(owner != null)
			str += ", owner=" + owner.getName();
		
		return str;
	}
}
