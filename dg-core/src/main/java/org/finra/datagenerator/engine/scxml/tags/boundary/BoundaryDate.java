/*
 * Copyright 2014 DataGenerator Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.finra.datagenerator.engine.scxml.tags.boundary;

import org.finra.datagenerator.engine.scxml.tags.CustomTagExtension;
import org.finra.datagenerator.engine.scxml.tags.boundary.action.BoundaryActionDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 */
public abstract class BoundaryDate<T extends BoundaryActionDate> implements CustomTagExtension<T> {

    /**
     * @param action   an Action of the type handled by this class
     * @param positive a boolean denoting positive or negative cases
     * @return a list of Strings that contain the states
     */
    public List<String> setParameters(T action, boolean positive) {
        String earliest = action.getEarliest();
        String latest = action.getLatest();

        boolean nullable = true;
        if (!action.getNullable().equals("true")) {
            nullable = false;
        }
        if (earliest == null) {
            earliest = "1970-01-01";
        }
        if (latest == null) {
            Calendar currDate = Calendar.getInstance();
            latest = new SimpleDateFormat("yyyy-MM-dd").format(currDate.getTime());
        }
        if (positive) {
            return positiveCase(nullable, earliest, latest);
        } else {
            return negativeCase(nullable, earliest, latest);
        }
    }

    /**
     * @param isNullable isNullable
     * @param earliest   lower boundary date
     * @param latest     upper boundary date
     * @return a list of boundary dates
     */
    public List<String> positiveCase(boolean isNullable, String earliest, String latest) {
        List<String> values = new LinkedList<>();

        int earlyDay = Integer.parseInt(earliest.substring(8, 10));
        int earlyMonth = Integer.parseInt(earliest.substring(5, 7));
        int earlyYear = Integer.parseInt(earliest.substring(0, 4));

        String earlyMo = (Integer.toString(earlyMonth).length() < 2 ? "0" + earlyMonth : "" + earlyMonth);
        String earlyDy = (Integer.toString(earlyDay).length() < 2 ? "0" + earlyDay : "" + earlyDay);

        values.add("" + earlyYear + "-" + earlyMo + "-" + earlyDy);

        if (earlyDay < 28) {
            earlyDay++;
        } else {
            if (earlyMonth < 12) {
                earlyDay = 1;
                earlyMonth++;
            } else {
                earlyMonth = 1;
                earlyDay = 1;
                earlyYear++;
            }
        }
        earlyMo = (Integer.toString(earlyMonth).length() < 2 ? "0" + earlyMonth : "" + earlyMonth);
        earlyDy = (Integer.toString(earlyDay).length() < 2 ? "0" + earlyDay : "" + earlyDay);

        values.add("" + earlyYear + "-" + earlyMo + "-" + earlyDy);

        int lateDay = Integer.parseInt(latest.substring(8, 10));
        int lateMonth = Integer.parseInt(latest.substring(5, 7));
        int lateYear = Integer.parseInt(latest.substring(0, 4));

        if (lateDay > 1) {
            lateDay--;
        } else {
            if (lateMonth > 1) {
                lateDay = 28;
                lateMonth--;
            } else {
                if (lateYear > 1970) {
                    lateMonth = 12;
                    lateDay = 31;
                    lateYear--;
                }
            }
        }
        String lateMo = (Integer.toString(lateMonth).length() < 2 ? "0" + lateMonth : "" + lateMonth);
        String lateDy = (Integer.toString(lateDay).length() < 2 ? "0" + lateDay : "" + lateDay);

        values.add("" + lateYear + "-" + lateMo + "-" + lateDy);

        lateDay = Integer.parseInt(latest.substring(8, 10));
        lateMonth = Integer.parseInt(latest.substring(5, 7));
        lateYear = Integer.parseInt(latest.substring(0, 4));

        lateMo = (Integer.toString(lateMonth).length() < 2 ? "0" + lateMonth : "" + lateMonth);
        lateDy = (Integer.toString(lateDay).length() < 2 ? "0" + lateDay : "" + lateDay);

        values.add("" + lateYear + "-" + lateMo + "-" + lateDy);

        if (!isNullable) {
            values.add("");
        }

        return values;
    }

    /**
     * @param isNullable isNullable
     * @param earliest   lower boundary date
     * @param latest     upper boundary date
     * @return a list of boundary dates
     */
    public List<String> negativeCase(boolean isNullable, String earliest, String latest) {
        List<String> values = new LinkedList<>();

        int earlyDay = Integer.parseInt(earliest.substring(8, 10));
        int earlyMonth = Integer.parseInt(earliest.substring(5, 7));
        int earlyYear = Integer.parseInt(earliest.substring(0, 4));

        if (earlyDay > 1) {
            earlyDay--;
        } else {
            if (earlyMonth > 1) {
                earlyDay = 28;
                earlyMonth--;
            } else {
                earlyMonth = 12;
                earlyDay = 31;
                earlyYear--;
            }
        }
        String earlyMo = (Integer.toString(earlyMonth).length() < 2 ? "0" + earlyMonth : "" + earlyMonth);
        String earlyDy = (Integer.toString(earlyDay).length() < 2 ? "0" + earlyDay : "" + earlyDay);
        values.add("" + earlyYear + "-" + earlyMo + "-" + earlyDy);

        int lateDay = Integer.parseInt(latest.substring(8, 10));
        int lateMonth = Integer.parseInt(latest.substring(5, 7));
        int lateYear = Integer.parseInt(latest.substring(0, 4));

        if (lateDay < 28) {
            lateDay++;
        } else {
            if (lateMonth < 12) {
                lateDay = 1;
                lateMonth++;
            } else {
                lateMonth = 1;
                lateDay = 1;
                lateYear++;
            }
        }
        String lateMo = (Integer.toString(lateMonth).length() < 2 ? "0" + lateMonth : "" + lateMonth);
        String lateDy = (Integer.toString(lateDay).length() < 2 ? "0" + lateDay : "" + lateDay);

        values.add("" + lateYear + "-" + lateMo + "-" + lateDy);
        values.add("" + lateMo + "-" + lateDy + "-" + lateYear);

        if (!isNullable) {
            values.add("");
        }

        return values;
    }

    /**
     * @param action            an Action of the type handled by this class
     * @param possibleStateList a current list of possible states produced so far from
     *                          expanding a model state
     * @param variableValue     a list storing the values
     * @return a list of Maps containing the cross product of all states
     */
    public List<Map<String, String>> returnStates(T action,
                                                  List<Map<String, String>> possibleStateList, List<String> variableValue) {
        List<Map<String, String>> states = new LinkedList<>();
        for (Map<String, String> p : possibleStateList) {
            for (String s : variableValue) {
                HashMap<String, String> n = new HashMap<>(p);
                n.put(action.getName(), s);
                states.add(n);
            }
        }
        return states;
    }
}
