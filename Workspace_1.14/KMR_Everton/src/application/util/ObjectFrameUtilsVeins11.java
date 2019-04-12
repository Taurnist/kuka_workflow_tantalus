package application.util;
//package com.kukasystems.mazedemo.utils;

import com.kuka.roboticsAPI.RoboticsAPIContext;
import com.kuka.roboticsAPI.geometricModel.AbstractFrame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.persistenceModel.IPersistenceEngine;
import com.kuka.roboticsAPI.persistenceModel.XmlApplicationDataSource;

/**
 * Utility class for manipulating {@link ObjectFrame}s.
 * 
 */
public final class ObjectFrameUtilsVeins11
{

    // there should be no instances
    private ObjectFrameUtilsVeins11()
    {

    }

    /**
     * Checks, if the given frame path exists within the underlying configuration
     * 
     * @param context
     *            the {@link RoboticsAPIContext}
     * @param framePath
     *            the frame path
     * @return <code>true</code> if the {@link ObjectFrame} exists; <code>false</code> otherwise.
     */
    public static boolean existsObjectFrame(RoboticsAPIContext context, String framePath)
    {
        if (context == null)
        {
            throw new IllegalArgumentException("context must not be null!");
        }
        if (framePath == null)
        {
            throw new IllegalArgumentException("framePath must not be null!");
        }
        if (!framePath.startsWith(AbstractFrame.NAME_SEPARATOR))
        {
            throw new IllegalArgumentException("framePath must start with " + AbstractFrame.NAME_SEPARATOR);
        }

        return null != context.getEngine(IPersistenceEngine.class).getDataSource(XmlApplicationDataSource.class)
                .tryLoadFrame(framePath);
    }

    /**
     * Creates a new {@link ObjectFrame} with the given name (<b>no path!</b>) and the given parent.
     * 
     * @param context
     *            the {@link RoboticsAPIContext}
     * @param newFrameName
     *            the name (<b>no path!</b>) of the new frame
     * @param parent
     *            the parent of the new frame
     * @param positionToSet
     *            the position to set to the new frame
     * @return the new {@link ObjectFrame}
     */
    public static ObjectFrame createObjectFrame(RoboticsAPIContext context, String newFrameName, ObjectFrame parent,
            AbstractFrame positionToSet)
    {
        return createObjectFrame(context, newFrameName, parent, positionToSet, null);
    }

    /**
     * Creates a new {@link ObjectFrame} with the given name (<b>no path!</b>) and the given parent.
     * 
     * @param context
     *            the {@link RoboticsAPIContext}
     * @param newFrameName
     *            the name (<b>no path!</b>) of the new frame
     * @param parent
     *            the parent of the new frame
     * @param positionToSet
     *            the position to set to the new frame
     * @param frameWithTeachInfo
     *            an {@link ObjectFrame} from which the teach information is copied or <code>null</code>, if no teach
     *            information is available.
     * @return the new {@link ObjectFrame}
     */
    public static ObjectFrame createObjectFrame(RoboticsAPIContext context, String newFrameName, ObjectFrame parent,
            AbstractFrame positionToSet, ObjectFrame frameWithTeachInfo)
    {

        if (context == null)
        {
            throw new IllegalArgumentException("context must not be null!");
        }
        if (newFrameName == null || newFrameName.isEmpty())
        {
            throw new IllegalArgumentException("newFrameName must not be null or empty!");
        }
        if (newFrameName.startsWith(AbstractFrame.NAME_SEPARATOR))
        {
            throw new IllegalArgumentException("newFrameName must not start with " + AbstractFrame.NAME_SEPARATOR);
        }
        if (parent == null)
        {
            throw new IllegalArgumentException("paremt must not be null!");
        }
        if (positionToSet == null)
        {
            throw new IllegalArgumentException("currentPosition must not be null!");
        }

        XmlApplicationDataSource xmlDataSource = context.getEngine(IPersistenceEngine.class).getDataSource(
                XmlApplicationDataSource.class);

        ObjectFrame newFrame = xmlDataSource.addFrame(parent);

        xmlDataSource.renameFrame(newFrame, newFrameName);

        //ObjectFrame newFrame = xmlDataSource.addFrame(newFrameName, parent);

        changeObjectFrame(xmlDataSource, newFrame, positionToSet, frameWithTeachInfo);

        return newFrame;
    }

    /**
     * 
     * @param context
     *            the {@link RoboticsAPIContext}
     * @param frameToChange
     *            the frame which underlying configuration data has to be changed.
     * @param positionToSet
     *            the new position to set
     * @param frameWithTeachInfo
     *            an {@link ObjectFrame} from which the teach information is copied.
     */
    public static void changeObjectFrame(RoboticsAPIContext context, ObjectFrame frameToChange,
            AbstractFrame positionToSet)
    {
        changeObjectFrame(context, frameToChange, positionToSet, null);
    }

    /**
     * 
     * @param context
     *            the {@link RoboticsAPIContext}
     * @param frameToChange
     *            the frame which underlying configuration data has to be changed.
     * @param positionToSet
     *            the new position to set
     * @param frameWithTeachInfo
     *            an {@link ObjectFrame} from which the teach information is copied.
     */
    public static void changeObjectFrame(RoboticsAPIContext context, ObjectFrame frameToChange,
            AbstractFrame positionToSet, ObjectFrame frameWithTeachInfo)
    {

        if (context == null)
        {
            throw new IllegalArgumentException("context must not be null!");
        }
        if (frameToChange == null)
        {
            throw new IllegalArgumentException("framePath must not be null!");
        }
        if (positionToSet == null)
        {
            throw new IllegalArgumentException("currentPosition must not be null!");
        }

        XmlApplicationDataSource xmlDataSource = context.getEngine(IPersistenceEngine.class).getDataSource(
                XmlApplicationDataSource.class);

        changeObjectFrame(xmlDataSource, frameToChange, positionToSet, frameWithTeachInfo);
    }

    /**
     * Removes the given {@link ObjectFrame} from the underlying configuration.
     * 
     * @param context
     *            the {@link RoboticsAPIContext}
     * @param frameToRemove
     *            the frame to be removed.
     */
    public static void removeObjectFrame(RoboticsAPIContext context, ObjectFrame frameToRemove)
    {
        XmlApplicationDataSource xmlDataSource = context.getEngine(IPersistenceEngine.class).getDataSource(
                XmlApplicationDataSource.class);
        xmlDataSource.removeFrame(frameToRemove);
        xmlDataSource.saveFile();
    }

    public static void changeObjectFrame(XmlApplicationDataSource xmlDataSource, ObjectFrame frameToChange,
            AbstractFrame currentPosition, ObjectFrame frameWithTeachInfo)
    {
        // copy all redundancy information
        frameToChange.getRedundancyInformation().putAll(currentPosition.getRedundancyInformation());

        // add teach info
        if (frameWithTeachInfo != null)
        {
            Object teachInfo = frameWithTeachInfo.getAdditionalFrameData().get(AbstractFrame.TEACHINFO_PARAMETER);
            if (teachInfo == null)
            {
                throw new IllegalStateException("frameWithTeachInfo has no teach info!");
            }
            frameToChange.getAdditionalFrameData().put(AbstractFrame.TEACHINFO_PARAMETER, teachInfo);
        }
        // set transformation
        xmlDataSource.changeFrameTransformation(frameToChange,
                frameToChange.getParent().transformationTo(currentPosition));

        xmlDataSource.saveFile();

    }

}
