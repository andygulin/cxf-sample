package cxf.sample.bean;

import cxf.sample.adapter.DateAdapter;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

    private static final long serialVersionUID = -1896190449428576501L;

    @XmlElement(name = "id", nillable = true, required = true, type = Integer.class, defaultValue = "0")
    private Integer id;

    @XmlElement(nillable = true, type = String.class)
    private String name;

    @XmlElement(nillable = true, type = Integer.class)
    private Integer age;

    @XmlElement(nillable = true, type = String.class)
    private String address;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date createdAt;

}