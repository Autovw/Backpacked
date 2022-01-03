package com.mrcrayfish.backpacked.common;

import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.function.BiFunction;

/**
 * Author: MrCrayfish
 */
public class ProgressFormatters
{
    public static final BiFunction<Integer, Integer, Component> COLLECT_X_OF_X = (count, maxCount) -> {
        count = Mth.clamp(count, 0, maxCount);
        return new TranslatableComponent("backpacked.formatter.collected_x_of_x", count, maxCount);
    };

    public static final BiFunction<Integer, Integer, Component> FED_X_OF_X = (count, maxCount) -> {
        count = Mth.clamp(count, 0, maxCount);
        return new TranslatableComponent("backpacked.formatter.fed_x_of_x", count, maxCount);
    };

    public static final BiFunction<Integer, Integer, Component> FOUND_X_OF_X = (count, maxCount) -> {
        count = Mth.clamp(count, 0, maxCount);
        return new TranslatableComponent("backpacked.formatter.found_x_of_x", count, maxCount);
    };

    public static final BiFunction<Integer, Integer, Component> USED_X_TIMES = (count, unused) -> {
        return new TranslatableComponent("backpacked.formatter.used_x_times", count);
    };

    public static final BiFunction<Integer, Integer, Component> BRED_X_OF_X = (count, maxCount) -> {
        return new TranslatableComponent("backpacked.formatter.bred_x_of_x", count, maxCount);
    };

    public static final BiFunction<Integer, Integer, Component> CUT_X_OF_X = (count, maxCount) -> {
        return new TranslatableComponent("backpacked.formatter.cut_x_of_x", count, maxCount);
    };

    public static final BiFunction<Integer, Integer, Component> SHEARED_X_SHEEP = (count, maxCount) -> {
        return new TranslatableComponent("backpacked.formatter.shear_x_sheep", count, maxCount);
    };

    public static final BiFunction<Integer, Integer, Component> INT_PERCENT = (numerator, denominator) -> {
        int percent = (int) (100 * (double) numerator / (double) denominator);
        return new TranslatableComponent("backpacked.formatter.int_percent", percent, "%");
    };
}