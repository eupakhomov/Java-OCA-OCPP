package eu.chargetime.ocpp.model.firmware;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.utilities.SugarUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.Objects;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>
 * Copyright (C) 2018 Mikhail Kladkevich <kladmv@ecp-share.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Sent by the Central System to the Charge Point.
 */
@XmlRootElement
@XmlType(propOrder = {"location", "retries", "retrieveDate", "retryInterval"})
public class UpdateFirmwareRequest implements Request {
    private String location;
    private Integer retries;
    private Calendar retrieveDate;
    private int retryInterval;

    public UpdateFirmwareRequest() {}

    public UpdateFirmwareRequest(String location, Calendar retrieveDate) {
        this.location = location;
        this.retrieveDate = retrieveDate;
    }

    @Override
    public boolean validate() {
        return (location != null) & (retrieveDate != null);
    }

    /**
     * This contains a string containing a URI pointing to a location from which to retrieve the firmware.
     *
     * @return String, a URI with the firmware.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Required.
     * This contains a string containing a URI pointing to a location from which to retrieve the firmware.
     *
     * @param location String, a URI with the firmware.
     */
    @XmlElement
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This specifies how many times Charge Point must try to download the firmware before giving up.
     * If this field is not present, it is left to Charge Point to decide how many times it wants to retry.
     *
     * @return int, retry times.
     */
    public int getRetries() {
        return retries;
    }

    /**
     * Optional.
     * This specifies how many times Charge Point must try to download the firmware before giving up.
     * If this field is not present, it is left to Charge Point to decide how many times it wants to retry.
     *
     * @param retries int, retry times.
     */
    @XmlElement
    public void setRetries(int retries) throws PropertyConstraintException {
        if (retries <= 0)
            throw new PropertyConstraintException("retries", retries);

        this.retries = retries;
    }

    /**
     * This contains the date and time after which the Charge Point must retrieve the (new) firmware.
     *
     * @return Calendar, date and time of retrieving.
     */
    public Calendar getRetrieveDate() {
        return retrieveDate;
    }

    /**
     * Required.
     * This contains the date and time after which the Charge Point must retrieve the (new) firmware.
     *
     * @param retrieveDate Calendar, date and time of retrieving.
     */
    @XmlElement
    public void setRetrieveDate(Calendar retrieveDate) {
        this.retrieveDate = retrieveDate;
    }

    /**
     * The interval in seconds after which a retry may be attempted. If this field is not present,
     * it is left to Charge Point to decide how long to wait between attempts.
     *
     * @return int, retry interval.
     */
    public int getRetryInterval() {
        return retryInterval;
    }

    /**
     * Optional.
     * The interval in seconds after which a retry may be attempted. If this field is not present,
     * it is left to Charge Point to decide how long to wait between attempts.
     *
     * @param retryInterval int, retry interval.
     * @throws PropertyConstraintException Value was zero or negative.
     * 
     */
    @XmlElement
    public void setRetryInterval(int retryInterval) throws PropertyConstraintException {
        if (retryInterval <= 0)
            throw new PropertyConstraintException("retryInterval", retryInterval);

        this.retryInterval = retryInterval;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateFirmwareRequest that = (UpdateFirmwareRequest) o;
        return retryInterval == that.retryInterval &&
            Objects.equals(location, that.location) &&
            Objects.equals(retries, that.retries) &&
            Objects.equals(retrieveDate, that.retrieveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, retries, retrieveDate, retryInterval);
    }

    @Override
    public String toString() {
        return "UpdateFirmwareRequest{" +
                "location='" + location + '\'' +
                ", retries=" + retries +
                ", retrieveDate='" + SugarUtil.calendarToString(retrieveDate) +
                ", retryInterval=" + retryInterval +
                ", isValid=" + String.valueOf(validate()) +
                '}';
    }
}
