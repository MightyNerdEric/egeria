/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.frameworks.connectors.properties;


import org.odpi.openmetadata.frameworks.connectors.properties.beans.ComplexSchemaType;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.SchemaType;

import java.util.Objects;

/**
 * An asset's schema provides information about how the asset structures the data it supports.
 * The AssetComplexSchemaType object describes a nested structure of schema attributes and types.
 */
public class AssetComplexSchemaType extends AssetSchemaType
{
    protected AssetSchemaAttributes schemaAttributes      = null;


    /**
     * Constructor used by the subclasses
     *
     * @param parentAsset descriptor of asset that this property relates to.
     */
    protected AssetComplexSchemaType(AssetDescriptor parentAsset)
    {
        super(parentAsset);
    }



    /**
     * Bean constructor
     *
     * @param schemaBean bean containing the schema properties
     * @param schemaAttributes the attributes that make up this schema
     */
    public AssetComplexSchemaType(SchemaType            schemaBean,
                                  AssetSchemaAttributes schemaAttributes)
    {
        super(schemaBean);

        this.schemaAttributes = schemaAttributes;
    }


    /**
     * Bean constructor with parent asset
     *
     * @param parentAsset descriptor for parent asset
     * @param schemaBean bean containing the schema properties
     * @param schemaAttributes the attributes that make up this schema
     */
    public AssetComplexSchemaType(AssetDescriptor       parentAsset,
                                  ComplexSchemaType     schemaBean,
                                  AssetSchemaAttributes schemaAttributes)
    {
        super(parentAsset, schemaBean);

        this.schemaAttributes = schemaAttributes;
    }


    /**
     * Copy/clone Constructor the parentAsset is passed separately to the template because it is also
     * likely to be being cloned in the same operation and we want the definitions clone to point to the
     * asset clone and not the original asset.
     *
     * @param parentAsset description of the asset that this schema is attached to.
     * @param templateSchema template object to copy.
     */
    public AssetComplexSchemaType(AssetDescriptor parentAsset, AssetComplexSchemaType templateSchema)
    {
        super(parentAsset, templateSchema);

        if (templateSchema != null)
        {
            this.schemaAttributes = templateSchema.getSchemaAttributes(super.getParentAsset());
        }
    }


    /**
     * Set up the bean that contains the properties of the schema.
     *
     * @param bean bean containing the schema properties
     */
    protected void  setBean(ComplexSchemaType bean)
    {
        super.setBean(bean);
    }


    /**
     * Return the list of schema attributes in this schema.
     *
     * @return SchemaAttributes
     */
    public AssetSchemaAttributes getSchemaAttributes()
    {
        if (schemaAttributes == null)
        {
            return null;
        }
        else
        {
            return schemaAttributes.cloneIterator(super.getParentAsset());
        }
    }


    /**
     * Return the list of schema attributes in this schema.
     *
     * @param parentAsset description of the asset that this schema is attached to.
     * @return SchemaAttributes
     */
    protected AssetSchemaAttributes getSchemaAttributes(AssetDescriptor parentAsset)
    {
        if (schemaAttributes == null)
        {
            return null;
        }
        else
        {
            return schemaAttributes.cloneIterator(parentAsset);
        }
    }


    /**
     * Return a clone of this schema element.  This method is needed because AssetSchemaType
     * is abstract.
     *
     * @param parentAsset description of the asset that this schema element is attached to.
     * @return An instance of the this object's subclass
     */
    @Override

    protected AssetSchemaType cloneAssetSchemaType(AssetDescriptor parentAsset)
    {
        return new AssetComplexSchemaType(parentAsset, this);
    }


    /**
     * Standard toString method.
     *
     * @return print out of variables in a JSON-style
     */
    @Override
    public String toString()
    {
        return "AssetComplexSchemaType{" +
                "schemaAttributes=" + schemaAttributes +
                ", parentAsset=" + parentAsset +
                ", displayName='" + getDisplayName() + '\'' +
                ", versionNumber='" + getVersionNumber() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", usage='" + getUsage() + '\'' +
                ", encodingStandard='" + getEncodingStandard() + '\'' +
                ", extendedProperties=" + getExtendedProperties() +
                ", qualifiedName='" + getQualifiedName() + '\'' +
                ", additionalProperties=" + getAdditionalProperties() +
                ", extendedProperties=" + getExtendedProperties() +
                ", type=" + getType() +
                ", GUID='" + getGUID() + '\'' +
                ", URL='" + getURL() + '\'' +
                ", assetClassifications=" + getAssetClassifications() +
                '}';
    }


    /**
     * Compare the values of the supplied object with those stored in the current object.
     *
     * @param objectToCompare supplied object
     * @return boolean result of comparison
     */
    @Override
    public boolean equals(Object objectToCompare)
    {
        if (this == objectToCompare)
        {
            return true;
        }
        if (objectToCompare == null || getClass() != objectToCompare.getClass())
        {
            return false;
        }
        if (!super.equals(objectToCompare))
        {
            return false;
        }
        AssetComplexSchemaType that = (AssetComplexSchemaType) objectToCompare;
        return Objects.equals(getSchemaAttributes(), that.getSchemaAttributes());
    }
}