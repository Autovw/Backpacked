package com.mrcrayfish.backpacked.client.gui.toasts;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.backpacked.client.gui.screen.CustomiseBackpackScreen;
import com.mrcrayfish.backpacked.client.model.BackpackModel;
import com.mrcrayfish.backpacked.common.Backpack;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;

import net.minecraft.client.gui.components.toasts.Toast.Visibility;

/**
 * Author: MrCrayfish
 */
public class UnlockBackpackToast implements Toast
{
    private static final Component TITLE = new TranslatableComponent("backpacked.toast.unlocked_backpack").withStyle(ChatFormatting.YELLOW);

    private final Component name;
    private final BackpackModel model;

    public UnlockBackpackToast(Backpack backpack)
    {
        this.name = new TranslatableComponent(backpack.getId().getNamespace() + ".backpack." + backpack.getId().getPath());
        this.model = backpack.getModel();
    }

    @Override
    public Visibility render(PoseStack matrixStack, ToastComponent gui, long delta)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        gui.blit(matrixStack, 0, 0, 0, 0, 160, 32);
        gui.getMinecraft().font.draw(matrixStack, TITLE, 35, 7, 0xFFFFFF);
        gui.getMinecraft().font.draw(matrixStack, this.name, 35.0F, 18.0F, 0xFFFFFF);
        CustomiseBackpackScreen.drawBackpackModel(matrixStack, this.model, 15, 7, 30F);
        return delta >= 5000L ? Visibility.HIDE : Visibility.SHOW;
    }
}
