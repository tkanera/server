/*
 * Copyright (C) 2013 tarent AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.osiam.storage.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.osiam.resources.scim.Email;
import org.osiam.resources.type.EmailType;

/**
 * Email Entity
 */
@Entity(name = "scim_email")
public class EmailEntity extends MultiValueAttributeEntitySkeleton implements HasUser, 
				ChildOfMultiValueAttributeWithIdAndTypeAndPrimary<EmailType>, Serializable{

    private static final long serialVersionUID = -6535056565639057057L;
    
    @Column
    @Enumerated(EnumType.STRING)
    private EmailType type;

    
    @Column(name = "postgresql_does_not_like_primary")
    private boolean primary;

    @ManyToOne(optional = false)
    private UserEntity user;

    @Override
    public EmailType getType() {
        return type;
    }

    @Override
    public void setType(EmailType type) {
        this.type = type;
    }

    @Override
    public boolean isPrimary() {
        return primary;
    }

    @Override
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public Email toScim() {
        return new Email.Builder().
                setPrimary(isPrimary()).
                setType(getType()).
                setValue(getValue()).
                build();
    }

    public static EmailEntity fromScim(Email email) {
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setType(email.getType());
        emailEntity.setValue(String.valueOf(email.getValue()));
        emailEntity.setPrimary((email.isPrimary() == null ? false : email.isPrimary()));
        return emailEntity;
    }

    @Override
    public UserEntity getUser() {
        return user;
    }

    @Override
    public void setUser(UserEntity user) {
        this.user = user;
    }

}