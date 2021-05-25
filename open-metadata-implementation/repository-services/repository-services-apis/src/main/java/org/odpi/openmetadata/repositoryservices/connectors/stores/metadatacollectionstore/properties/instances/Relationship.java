/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * Relationship is a POJO that manages the properties of an open metadata relationship.  This includes information
 * about the relationship type, the two entities it connects and the properties it holds.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Relationship extends InstanceHeader
{
    private static final long    serialVersionUID = 1L;

    private   InstanceProperties    relationshipProperties = null;

    private   EntityProxy           entityOneProxy         = null;
    private   EntityProxy           entityTwoProxy         = null;


    /**
     * Default constructor creates an empty relationship linked to nothing.
     */
    public Relationship()
    {
        super();

        /*
         * Nothing else to do as already initialized to null.
         */

    }


    /**
     * Copy/clone constructor builds a relationship from the supplied template.
     *
     * @param templateElement template relationship to copy
     */
    public Relationship(Relationship templateElement)
    {
        super(templateElement);

        if (templateElement != null)
        {
            relationshipProperties = templateElement.getProperties();
            entityOneProxy         = templateElement.getEntityOneProxy();
            entityTwoProxy         = templateElement.getEntityTwoProxy();
        }
    }


    /**
     * Test to determine if the supplied entity is linked by this relationship.
     *
     * @param entityGUID unique identifier for the entity to test.
     * @return boolean indicate whether the supplied entity is linked by this relationship
     */
    public boolean relatedToEntity(String  entityGUID)
    {
        if (entityGUID == null)
        {
            return false;
        }

        if (entityOneProxy != null)
        {
            if (entityOneProxy.getGUID().equals(entityGUID))
            {
                return true;
            }
        }

        if (entityTwoProxy != null)
        {
            return (entityTwoProxy.getGUID().equals(entityGUID));
        }

        return false;
    }


    /**
     * Return the GUID at the other end of the relationship to the supplied entity.
     *
     * @param entityGUID unique identifier for the entity to test.
     * @return String guid for the entity at the other end of the relationship.  Null if no matching entity found.
     */
    public String returnLinkedEntity(String  entityGUID)
    {
        if ((entityGUID == null) || (entityOneProxy == null) || (entityTwoProxy == null))
        {
            return null;
        }

        String   entityOneGUID = entityOneProxy.getGUID();
        String   entityTwoGUID = entityTwoProxy.getGUID();

        if ((entityOneGUID == null) || entityTwoGUID == null)
        {
            return null;
        }

        if (entityOneGUID.equals(entityGUID))
        {
            return entityTwoGUID;
        }

        if (entityTwoGUID.equals(entityGUID))
        {
            return entityOneGUID;
        }

        return null;
    }


    /**
     * Return a copy of all of the properties for this relationship.  Null means no properties exist.
     *
     * @return InstanceProperties
     */
    public InstanceProperties  getProperties()
    {
        if (relationshipProperties == null)
        {
            return null;
        }
        else if ((relationshipProperties.getInstanceProperties() == null) &&
                 (relationshipProperties.getEffectiveFromTime() == null) &&
                 (relationshipProperties.getEffectiveToTime() == null))
        {
            return null;
        }
        else
        {
            return new InstanceProperties(relationshipProperties);
        }
    }


    /**
     * Set up the properties for this relationship.
     *
     * @param newProperties InstanceProperties object
     */
    public void setProperties(InstanceProperties  newProperties)
    {
        relationshipProperties = newProperties;
    }



    /**
     * Return details of the entity at the first end of the relationship.
     *
     * @return entityOneProxy EntityProxy object for the first end of the relationship.
     */
    public EntityProxy getEntityOneProxy()
    {
        if (entityOneProxy == null)
        {
            return null;
        }
        else
        {
            return new EntityProxy(entityOneProxy);
        }
    }


    /**
     * Set up details of the entity at the first end of the relationship.
     *
     * @param entityOneProxy EntityProxy object for the first end of the relationship.
     */
    public void setEntityOneProxy(EntityProxy entityOneProxy) { this.entityOneProxy = entityOneProxy; }


    /**
     * Return details of the entity at second end of the relationship.
     *
     * @return EntityProxy object for the second end of the relationship
     */
    public EntityProxy getEntityTwoProxy()
    {
        if (entityTwoProxy == null)
        {
            return null;
        }
        else
        {
            return new EntityProxy(entityTwoProxy);
        }
    }


    /**
     * Set up the identity of the proxy at the other end of the relationship.
     *
     * @param entityTwoProxy EntityProxy
     */
    public void setEntityTwoProxy(EntityProxy entityTwoProxy) { this.entityTwoProxy = entityTwoProxy; }


    /**
     * Standard toString method.
     *
     * @return JSON style description of variables.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Relationship{");
        toStringBuilder(sb);
        sb.append("}");
        return sb.toString();
    }


    /**
     * Extensible toString method.
     *
     * @param sb buffer through which to build up the string
     */
    @Override
    protected void toStringBuilder(StringBuilder sb)
    {
        sb.append(", relationshipProperties=").append(relationshipProperties);
        sb.append(", entityOneProxy=").append(entityOneProxy);
        sb.append(", entityTwoProxy=").append(entityTwoProxy);
        sb.append(", ");
        super.toStringBuilder(sb);
    }


    /**
     * Validate that an object is equal depending on their stored values.
     *
     * @param objectToCompare object
     * @return boolean result
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
        Relationship that = (Relationship) objectToCompare;
        return Objects.equals(relationshipProperties, that.relationshipProperties) &&
                Objects.equals(getEntityOneProxy(), that.getEntityOneProxy()) &&
                Objects.equals(getEntityTwoProxy(), that.getEntityTwoProxy());
    }


    /**
     * Return a hash code based on the values of this object.
     *
     * @return in hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), relationshipProperties, getEntityOneProxy(), getEntityTwoProxy());
    }
}
