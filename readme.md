bundle:install mvn:org.glassfish.jaxb/jaxb-core/2.2.11
bundle:install wrap:mvn:org.glassfish.jaxb/jaxb-runtime/2.2.11

feature:install cxf
feature:install cxf-jackson
feature:install cxf-dosgi-provider-rs
feature:repo-add aries-rsa
feature:repo-add cxf-dosgi

repo-add mvn:rpraut.osgi/osgi-cxf-rest/1.0.0-SNAPSHOT/xml/features
feature:install osgi-cxf-rest