package com.anjlab.android.iab.v3;

import android.os.Parcel;

import com.anjlab.android.iab.v3.util.ResourcesUtil;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PurchaseInfoParcelableTest
{

    private PurchaseInfo purchaseInfo;

    @Before
    public void init()
    {
        purchaseInfo = new PurchaseInfo(ResourcesUtil.loadFile("purchase_info.json"), "signature");
    }

    @Test
    public void testParcelable() throws Exception
    {
        Parcel parcel = Parcel.obtain();
        purchaseInfo.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        PurchaseInfo newInfo = PurchaseInfo.CREATOR.createFromParcel(parcel);

        assertEquals(purchaseInfo.getResponseData(), newInfo.getResponseData());
        assertEquals(purchaseInfo.getSignature(), newInfo.getSignature());
    }

    @Test
    public void testResponseDataParcelable() throws Exception
    {
        PurchaseData responseData = purchaseInfo.parseResponseDataImpl();

        Parcel parcel = Parcel.obtain();
        responseData.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        PurchaseData newData = PurchaseData.CREATOR.createFromParcel(parcel);

        assertEquals(responseData.getAutoRenewing(), newData.getAutoRenewing());
        assertEquals(responseData.getPurchaseToken(), newData.getPurchaseToken());
        assertEquals(responseData.getDeveloperPayload(), newData.getDeveloperPayload());
        assertEquals(responseData.getPurchaseState(), newData.getPurchaseState());
        assertEquals(responseData.getPurchaseTime(), newData.getPurchaseTime());
        assertEquals(responseData.getProductId(), newData.getProductId());
        assertEquals(responseData.getPackageName(), newData.getPackageName());
        assertEquals(responseData.getOrderId(), newData.getOrderId());
    }
}
