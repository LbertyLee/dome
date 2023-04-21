package com.lh.dome.common.validate;

import jakarta.validation.GroupSequence;

@GroupSequence({OrderGroup.First.class, OrderGroup.Second.class, OrderGroup.Third.class})
public interface OrderGroup {
    interface First {
    }

    interface Second {
    }

    interface Third {
    }
}
