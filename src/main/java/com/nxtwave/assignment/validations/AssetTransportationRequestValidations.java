package com.nxtwave.assignment.validations;

import java.util.Arrays;

/**
 * Validations class for asset transportation requests.
 */
public class AssetTransportationRequestValidations {

    public enum AssetType {
        LAPTOP,
        TRAVEL_BAG,
        PACKAGE;
    }

    public enum AssetSensitivity {
        HIGHLY_SENSITIVE,
        SENSITIVE,
        NORMAL;
    }

    /**
     * Validation method for asset type.
     * @param assetType
     * @return
     * @throws IllegalArgumentException
     */
    public static boolean isValidAssetType(String assetType) throws IllegalArgumentException {
        return Arrays.stream(
                AssetType.values())
                .map(AssetType::name)
                .anyMatch(assetType::equals);
    }

    /**
     * Validation method for asset sensitivity.
     * @param assetSensitivity
     * @return
     * @throws IllegalArgumentException
     */
    public static boolean isValidAssetSensitivity(String assetSensitivity) throws IllegalArgumentException {
        return Arrays.stream(
                AssetSensitivity.values())
                .map(AssetSensitivity::name)
                .anyMatch(assetSensitivity::equals);
    }
}