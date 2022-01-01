package com.mrcrayfish.backpacked.common;

/**
 * Author: MrCrayfish
 */
public enum BackpackModelProperty
{
    SHOW_WITH_ELYTRA("AlwaysRenderBackpack", false),
    SHOW_EFFECTS("ShowBackpackEffects", true);

    private final String tagName;
    private final boolean defaultValue;

    BackpackModelProperty(String tagName, boolean defaultValue)
    {
        this.tagName = tagName;
        this.defaultValue = defaultValue;
    }

    public String getTagName()
    {
        return this.tagName;
    }

    public boolean getDefaultValue()
    {
        return this.defaultValue;
    }
}
