package com.anjlab.android.iab.v3;

import android.os.Parcel;

import com.anjlab.android.iab.v3.util.ResourcesUtil;

import org.json.JSONObject;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SkuDetailsParcelableTest {
    @Test
    public void testParcelableInApp() throws Exception {
        testParcelable(loadSkuDetails("sku_in_app.json"), false, false);
    }

    @Test
    public void testParcelableSubscription() throws Exception {
        testParcelable(loadSkuDetails("sku_subscription.json"), false, false);
    }

    @Test
    public void testParcelableSubscriptionIntroductory() throws Exception {
        testParcelable(loadSkuDetails("sku_subscription_introductory.json"), true, false);
    }

    @Test
    public void testParcelableSubscriptionTrial() throws Exception {
        testParcelable(loadSkuDetails("sku_subscription_trial.json"), false, true);
    }

    private SkuDetails loadSkuDetails(String jsonFilePath) throws Exception {
        JSONObject details = new JSONObject(ResourcesUtil.loadFile(jsonFilePath));
        return new SkuDetails(details);
    }

    private void testParcelable(SkuDetails skuDetails, boolean isIntroPrice, boolean isTrial) throws Exception {
        Parcel parcel = Parcel.obtain();

        skuDetails.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        SkuDetails result = SkuDetails.CREATOR.createFromParcel(parcel);

        assertEquals(skuDetails.getProductId(), result.getProductId());
        assertEquals(skuDetails.getPriceLong(), result.getPriceLong());
        assertEquals(skuDetails.getPriceText(), result.getPriceText());
        assertEquals(skuDetails.getPriceValue(), result.getPriceValue());
        assertEquals(skuDetails.getDescription(), result.getDescription());
        assertEquals(skuDetails.isSubscription(), result.isSubscription());
        assertEquals(skuDetails.getCurrency(), result.getCurrency());
        assertEquals(skuDetails.getTitle(), result.getTitle());
        assertEquals(skuDetails.getSubscriptionPeriod(), result.getSubscriptionPeriod());
        assertEquals(skuDetails.getSubscriptionFreeTrialPeriod(), result.getSubscriptionFreeTrialPeriod());
        assertEquals(skuDetails.getHaveTrialPeriod(), result.getHaveTrialPeriod());
        assertEquals(skuDetails.getIntroductoryPriceValue(), result.getIntroductoryPriceValue());
        assertEquals(skuDetails.getIntroductoryPricePeriod(), result.getIntroductoryPricePeriod());
        assertEquals(skuDetails.getIntroductoryPriceCycles(), result.getIntroductoryPriceCycles());
        assertEquals(skuDetails.getIntroductoryPriceLong(), result.getIntroductoryPriceLong());
        assertEquals(skuDetails.getHaveIntroductoryPeriod(), result.getHaveIntroductoryPeriod());
        assertEquals(skuDetails.getIntroductoryPriceText(), result.getIntroductoryPriceText());

        assertEquals(skuDetails.getHaveIntroductoryPeriod(), isIntroPrice);
        assertEquals(skuDetails.getHaveTrialPeriod(), isTrial);
    }
}
