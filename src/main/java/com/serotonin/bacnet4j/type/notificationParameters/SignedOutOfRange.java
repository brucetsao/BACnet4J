/*
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2006-2011 Serotonin Software Technologies Inc. http://serotoninsoftware.com
 * @author Matthew Lohbihler
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * When signing a commercial license with Serotonin Software Technologies Inc.,
 * the following extension to GPL is made. A special exception to the GPL is 
 * included to allow you to distribute a combined work that includes BAcnet4J 
 * without being obliged to provide the source code for any proprietary components.
 */
package com.serotonin.bacnet4j.type.notificationParameters;

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.constructed.StatusFlags;
import com.serotonin.bacnet4j.type.primitive.SignedInteger;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.bacnet4j.util.sero.ByteQueue;

public class SignedOutOfRange extends NotificationParameters {
    private static final long serialVersionUID = -2963890472935974026L;

    public static final byte TYPE_ID = 15;

    private final SignedInteger exceedingValue;
    private final StatusFlags statusFlags;
    private final UnsignedInteger deadband;
    private final SignedInteger exceedingLimit;

    public SignedOutOfRange(SignedInteger exceedingValue, StatusFlags statusFlags, UnsignedInteger deadband,
            SignedInteger exceedingLimit) {
        this.exceedingValue = exceedingValue;
        this.statusFlags = statusFlags;
        this.deadband = deadband;
        this.exceedingLimit = exceedingLimit;
    }

    @Override
    protected void writeImpl(ByteQueue queue) {
        write(queue, exceedingValue, 0);
        write(queue, statusFlags, 1);
        write(queue, deadband, 2);
        write(queue, exceedingLimit, 3);
    }

    public SignedOutOfRange(ByteQueue queue) throws BACnetException {
        exceedingValue = read(queue, SignedInteger.class, 0);
        statusFlags = read(queue, StatusFlags.class, 1);
        deadband = read(queue, UnsignedInteger.class, 2);
        exceedingLimit = read(queue, SignedInteger.class, 3);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    public SignedInteger getExceedingValue() {
        return exceedingValue;
    }

    public StatusFlags getStatusFlags() {
        return statusFlags;
    }

    public UnsignedInteger getDeadband() {
        return deadband;
    }

    public SignedInteger getExceedingLimit() {
        return exceedingLimit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deadband == null) ? 0 : deadband.hashCode());
        result = prime * result + ((exceedingLimit == null) ? 0 : exceedingLimit.hashCode());
        result = prime * result + ((exceedingValue == null) ? 0 : exceedingValue.hashCode());
        result = prime * result + ((statusFlags == null) ? 0 : statusFlags.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SignedOutOfRange other = (SignedOutOfRange) obj;
        if (deadband == null) {
            if (other.deadband != null)
                return false;
        }
        else if (!deadband.equals(other.deadband))
            return false;
        if (exceedingLimit == null) {
            if (other.exceedingLimit != null)
                return false;
        }
        else if (!exceedingLimit.equals(other.exceedingLimit))
            return false;
        if (exceedingValue == null) {
            if (other.exceedingValue != null)
                return false;
        }
        else if (!exceedingValue.equals(other.exceedingValue))
            return false;
        if (statusFlags == null) {
            if (other.statusFlags != null)
                return false;
        }
        else if (!statusFlags.equals(other.statusFlags))
            return false;
        return true;
    }
}
