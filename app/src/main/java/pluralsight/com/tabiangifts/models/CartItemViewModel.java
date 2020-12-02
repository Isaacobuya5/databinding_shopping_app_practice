package pluralsight.com.tabiangifts.models;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import pluralsight.com.tabiangifts.BR;
import pluralsight.com.tabiangifts.IMainActivity;


/**
 * Created by User on 2/9/2018.
 */

public class CartItemViewModel extends BaseObservable{

    private static final String TAG = "CartItemViewModel";

    private CartItem cartItem;

    @Bindable
    public CartItem getCartItem(){
        return cartItem;
    }

    public void setCartItem(CartItem cartItem){
        Log.d(TAG, "setQuantity: updating cart");
        this.cartItem = cartItem;
        notifyPropertyChanged(BR.cartItem);
    }


    public String getQuantityString(CartItem cartItem){
        return ("Qty: " + String.valueOf(cartItem.getQuantity()));
    }

    // increase quantity
    public void increaseQuantity(Context context) {
        // get the current cart item
        CartItem cartItem = getCartItem();
        // increase the quantity of the cart item
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        // set the cartItem to the current cart item
        setCartItem(cartItem);
        // reference IMainActivity to update the quantity on IMainActivity
        IMainActivity iMainActivity = (IMainActivity) context;
        // now update the quantity
        iMainActivity.updateQuantity(cartItem.getProduct(), 1);
    }

    // increase quality
    public void decreaseQuantity(Context context) {
        // get the current cart item
        CartItem cartItem = getCartItem();
        IMainActivity iMainActivity = (IMainActivity) context;
        // ensure we only decrease if quantity is greater than 1
        if (cartItem.getQuantity() > 1) {
            // increase the quantity of the cart item
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            // set the cartItem to the current cart item
            setCartItem(cartItem);
            // now update the quantity
            iMainActivity.updateQuantity(cartItem.getProduct(), -1);
        } else if(cartItem.getQuantity() == 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            setCartItem(cartItem);
            // to remove it from the cart, we need another interface method
            iMainActivity.removeCartItem(cartItem);
        }
    }

}



























