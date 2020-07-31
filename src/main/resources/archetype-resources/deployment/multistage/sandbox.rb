prompt_if_unset :host
server "#{host}.drybox.cloud", :web, :app
set :deploy_to, '/opt/services/${artifactId}-silo'
set :fqdn, "#{host}.drybox.cloud"
