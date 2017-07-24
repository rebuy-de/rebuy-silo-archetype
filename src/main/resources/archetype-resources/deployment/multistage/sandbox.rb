prompt_if_unset :host
server "#{host}.sandbox.rebuy.loc", :web, :app
set :deploy_to, '/opt/services/${artifactId}-silo'
set :fqdn, "#{host}.sandbox.rebuy.loc"
