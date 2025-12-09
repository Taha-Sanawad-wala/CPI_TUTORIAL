import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder

def Message processData(Message message) {

    // Get incoming XML body
    def body = message.getBody(String)
    def root = new XmlSlurper().parseText(body)

    def writer = new StringWriter()
    def xml = new MarkupBuilder(writer)

    xml.root {
        metadata {
            fieldname(datatype: "VARCHAR", "USER_ID")
            fieldname(datatype: "VARCHAR", "USER_NAME")
            fieldname(datatype: "VARCHAR", "FIRST_NAME")
            fieldname(datatype: "VARCHAR", "LAST_NAME")
            fieldname(datatype: "VARCHAR", "FULL_NAME")
        }
        // Loop through all MtrgActyBusinessUserType nodes
        root.'MtrgActyBusinessUserType'.each { user ->
            row {
                USER_ID(user.UserID.text())
                USER_NAME(user.UserName.text())
                FIRST_NAME(user.FirstName.text())
                LAST_NAME(user.LastName.text())
                FULL_NAME(user.FullName.text())
            }
        }
    }

    // Set transformed XML as new body
    message.setBody(writer.toString())
    return message
}
